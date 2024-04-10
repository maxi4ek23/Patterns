package ua.lviv.iot.project_doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.project_doc.model.Company;
import ua.lviv.iot.project_doc.model.Compensation;
import ua.lviv.iot.project_doc.model.Employee;
import ua.lviv.iot.project_doc.repository.CsvRepository;

import java.util.List;

@Service
public class DataBaseService {
	
	@Autowired
	CsvRepository repository;
	
	CompanyService companyService;
	EmployeeService employeeService;
	CompensationService compensationService;
	
	@Autowired
    public DataBaseService(CompanyService companyService,EmployeeService employeeService,
    		CompensationService compensationService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.compensationService = compensationService;
    }
	
	public void dumpCsvToDB(String filepath) {
		List<String[]> data = repository.readAll(filepath);
        data.forEach(entry -> {
            switch (entry[0]) {
                case "Company":
                	Company company = companyService.mapCsvToObject(entry);
                	companyService.saveToDatabase(company);
                    break;
                case "Employee": {
                    Employee employee = employeeService.mapCsvToObject(entry);
                    employeeService.saveToDatabase(employee);
                    break;
                }
                case "Compensation": {
                	Compensation compensation = compensationService.mapCsvToObject(entry);
                	compensationService.saveToDatabase(compensation);
                    break;
                }
            }
        });
    }
}
