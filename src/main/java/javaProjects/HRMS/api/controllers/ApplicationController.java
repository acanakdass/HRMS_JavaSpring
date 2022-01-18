package javaProjects.HRMS.api.controllers;

import javaProjects.HRMS.business.abstracts.ApplicationService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.entities.concretes.Application;
import javaProjects.HRMS.entities.dtos.ApplicationAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/applications")
@CrossOrigin
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {

        this.applicationService = applicationService;
    }
    @GetMapping("/getAll")
    public DataResult<List<Application>> getAll(){
        return applicationService.getAll();
    }

    @GetMapping("/getByAdvertId")
    public DataResult<List<Application>> getByAdvertId(@RequestParam Integer advertId){
        return applicationService.getByAdvertId(advertId);
    }


    @PostMapping("/add")
    public Result add(ApplicationAddDto applicationAddDto){
        return applicationService.add(applicationAddDto);
    }
}
