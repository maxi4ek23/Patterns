package ua.lviv.iot.project_doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.project_doc.model.Company;
import ua.lviv.iot.project_doc.repository.CompanyRepository;


@Service
public class CompanyService extends GeneralService<Company> {

	@Autowired
    public CompanyService(CompanyRepository companyRepository) {
        super(companyRepository);
    }

	@Override
    public Company mapCsvToObject(String[] objectCsv) {
        String name = objectCsv[0];
        String location = objectCsv[1];

        return new Company( name, location);
    }
 
}
