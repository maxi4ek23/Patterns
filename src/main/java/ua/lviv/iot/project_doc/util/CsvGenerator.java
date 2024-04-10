package ua.lviv.iot.project_doc.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jeasy.random.EasyRandom;

import com.opencsv.CSVWriter;

import ua.lviv.iot.project_doc.model.Company;
import ua.lviv.iot.project_doc.model.Compensation;
import ua.lviv.iot.project_doc.model.Employee;

public class CsvGenerator {

	public static void main(String[] args) throws IOException {
		List<String[]> data = new LinkedList<>();
		
		generateCompanyData(data);
        generateEmployeeData(data);
        generateCompensationData(data);
        
        try (CSVWriter writer = new CSVWriter(new FileWriter("data.csv"))) {
            writer.writeAll(data);
        }

	}
	
	private static void generateCompanyData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<Company> companies = generator.objects(Company.class, 500).toList();
		for (Company company : companies) {
            data.add(company.toCsvFormat());
        }
	}
	
	private static void generateEmployeeData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<Employee> employees = generator.objects(Employee.class, 500).toList();
		for (Employee employee : employees) {
            data.add(employee.toCsvFormat());
        }
	}
	
	private static void generateCompensationData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<Compensation> compensations = generator.objects(Compensation.class, 500).toList();
		for (Compensation compensation : compensations) {
            data.add(compensation.toCsvFormat());
        }
	}

}
