package ua.lviv.iot.project_doc.service;


import org.springframework.stereotype.Service;

import ua.lviv.iot.project_doc.model.Company;
import ua.lviv.iot.project_doc.model.Compensation;
import ua.lviv.iot.project_doc.repository.CompensationRepository;


@Service
public class CompensationService extends GeneralService<Compensation> {
	
	private final CompanyService companyService;
	public CompensationService(CompensationRepository compensationRepository, CompanyService companyService) {
		super(compensationRepository);
        this.companyService = companyService;
	}

	@Override
    public Compensation mapCsvToObject(String[] objectCsv) {
        String amount = objectCsv[0];
        String type = objectCsv[1];
        Company company = companyService.getById(Integer.parseInt(objectCsv[2]));

        return new Compensation( amount, type, company);
    }

}
