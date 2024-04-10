package ua.lviv.iot.project_doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.project_doc.model.Company;
import ua.lviv.iot.project_doc.model.Employee;
import ua.lviv.iot.project_doc.repository.EmployeeRepository;


@Service
public class EmployeeService extends GeneralService<Employee> {

	private final CompanyService companyService;
	@Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyService companyService) {
        super(employeeRepository);
        this.companyService = companyService;
    }
	
	 @Override
	    public Employee mapCsvToObject(String[] objectCsv) {
	        String name = objectCsv[0];
	        String surname = objectCsv[1];
	        String salary = objectCsv[2];
	        String position = objectCsv[3];
	        Company company = companyService.getById(Integer.parseInt(objectCsv[4]));

	        return new Employee( name, surname, salary, position, company);
	    }

}
