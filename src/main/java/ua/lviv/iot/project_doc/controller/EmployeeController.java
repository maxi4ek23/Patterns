package ua.lviv.iot.project_doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.project_doc.model.Employee;
import ua.lviv.iot.project_doc.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController extends GeneralController<Employee> {
	private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
        this.employeeService = employeeService;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer employeeId,
                                                       @RequestBody Employee employee) {
    	employee.setId(employeeId);
        Employee updatedEmployee = employeeService.update(employeeId, employee, new Employee());
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
