package ua.lviv.iot.project_doc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ua.lviv.iot.project_doc.service.DataBaseService;

@SpringBootApplication
public class ManagmentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ManagmentApplication.class, args);
		context.getBean(DataBaseService.class).dumpCsvToDB("data.csv");
	}

}