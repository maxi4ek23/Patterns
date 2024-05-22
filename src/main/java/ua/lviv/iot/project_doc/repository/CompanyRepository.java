package ua.lviv.iot.project_doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.project_doc.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
