package ua.lviv.iot.project_doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.iot.project_doc.model.Compensation;
import ua.lviv.iot.project_doc.service.CompensationService;


@RestController
@RequestMapping("/compensation")
public class CompensationController extends GeneralController<Compensation> {
	private final CompensationService compensationService;

    @Autowired
    public CompensationController(CompensationService compensationService) {
        super(compensationService);
        this.compensationService = compensationService;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Compensation> updateCompensation(@PathVariable("id") Integer compensationId,
                                                       @RequestBody Compensation compensation) {
    	compensation.setId(compensationId);
    	Compensation updatedCompensation = compensationService.update(compensationId, compensation, new Compensation());
        if (updatedCompensation != null) {
            return new ResponseEntity<>(updatedCompensation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
