import os
import csv
from faker import Faker
from constants import *


faker = Faker()


def generate_user_data(directory_name, num_rows, file_name):
    """Generate random user data and write to CSV file."""
    with open(os.path.join(directory_name, file_name), 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(('row_number', 'country', 'city', 'street', 'house_number', 'address', 'postcode'))
        row_number = 1
        for _ in range(num_rows):
            writer.writerow([
                row_number,
                faker.country(),
                faker.city(),
                faker.street_name(),
                faker.building_number(),
                faker.address(),
                faker.postcode()
            ])
            row_number += 1


def generate_data(directory_name=GENERATED_DATA_DIR):
    generate_user_data(directory_name, USER_DATA_ROWS_NUMBER, USER_DATA_FILE_NAME)


def get_user_data_as_dict():
    data = []
    with open(os.path.join(GENERATED_DATA_DIR, USER_DATA_FILE_NAME), 'r') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            data.append(row)
    return data

if __name__ == "__main__":
    os.makedirs(GENERATED_DATA_DIR, exist_ok=True)
    generate_data()
