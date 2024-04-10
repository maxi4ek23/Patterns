package ua.lviv.iot.project_doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.project_doc.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
