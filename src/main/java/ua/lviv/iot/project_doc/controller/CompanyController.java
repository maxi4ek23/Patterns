package ua.lviv.iot.project_doc.controller;

import ua.lviv.iot.project_doc.model.Company;
import ua.lviv.iot.project_doc.service.CompanyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class CompanyController  {
	private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {

        this.companyService = companyService;
    }

    @GetMapping("/company")
    public String getAllCompanies(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "company";
    }

    @GetMapping("/showNewCompanyForm")
    public String showNewCompanyForm(Model model) {
    	Company company = new Company();
        model.addAttribute("company", company);
        return "create_company";
    }

    @GetMapping("/showUpdateCompanyForm/{id}")
    public String showUpdateCompanyForm(@PathVariable("id") Integer companyId, Model model) {
    	Company company = companyService.getById(companyId);
        model.addAttribute("company", company);
        return "update_company";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
    	companyService.saveToDatabase(company);
        return "redirect:/company";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") Integer id) {
    	companyService.deleteById(id);
        return "redirect:/company";
    }
}
