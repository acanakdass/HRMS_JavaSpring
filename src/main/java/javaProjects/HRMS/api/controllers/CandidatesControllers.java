package javaProjects.HRMS.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import javaProjects.HRMS.business.abstracts.CandidateService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Candidate;


@RestController
@RequestMapping("/api/candidates")
public class CandidatesControllers {

private CandidateService candidateService;	
	
	

	public CandidatesControllers(CandidateService candidateService) {
	this.candidateService = candidateService;
}

	@GetMapping("/getByIdentityNumber")
	public DataResult<Candidate> getByIdentityNumber(@RequestParam String identityNumber){
		return this.candidateService.getByIdentityNumber(identityNumber);
	}

	@GetMapping("/getAll")
	public DataResult<List<Candidate>> getAll(){
		return this.candidateService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Candidate candidate) throws NumberFormatException, RemoteException{
		System.out.println("candidate Id : ");
		System.out.println(candidate.getId());
		Result result = this.candidateService.add(candidate);
		return result;
	}
}
