package ua.lviv.iot.project_doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.iot.project_doc.model.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Integer> {

}
