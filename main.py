from abc import ABC, abstractmethod
import yaml, json
from kafka import KafkaProducer
from data_utils import get_user_data_as_dict
from constants import *


class OutputDataStrategy(ABC):
    @abstractmethod
    def output(self, data):
        pass


class ConsoleOutputDataStrategy(OutputDataStrategy):
    def output(self, data: list):
        for row in data:
            print(row)


class KafkaOutputDataStrategy(OutputDataStrategy):
    def __init__(self, kafka_servers, kafka_topic):
        self.kafka_servers = kafka_servers
        self.kafka_topic = kafka_topic
        self.producer = KafkaProducer(bootstrap_servers=self.kafka_servers)

    def output(self, data: list):
        for row in data:
            row = json.dumps(row).encode('utf-8')
            self.producer.send(self.kafka_topic, row)
        self.producer.flush()


class OutputContext:
    def __init__(self, strategy: OutputDataStrategy):
        self.strategy = strategy

    def execute_strategy(self, strategy: OutputDataStrategy):
        self.strategy = strategy

    def output_data(self, data: list):
        self.strategy.output(data)


def main():
    with open(CONFIG_FILENAME, 'r') as f:
        config = yaml.safe_load(f)

    output_strategy = config[OUTPUT][STRATEGY]

    if output_strategy == KAFKA:
        kafka_servers = config[KAFKA][SERVERS]
        kafka_topic = config[KAFKA][TOPIC]
        output_strategy = KafkaOutputDataStrategy(kafka_servers, kafka_topic)
    elif output_strategy == CONSOLE:
        output_strategy = ConsoleOutputDataStrategy()
    else:
        raise ValueError(STRATEGY_ERROR_MSG)

    context = OutputContext(output_strategy)
    data = get_user_data_as_dict()

    context.output_data(data)


if __name__ == "__main__":
    main()