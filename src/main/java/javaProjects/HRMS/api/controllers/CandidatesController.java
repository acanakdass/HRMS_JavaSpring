package javaProjects.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaProjects.HRMS.business.abstracts.Users.CandidateService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Users.Candidate;


@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidatesController {

private CandidateService candidateService;	
	
	
@Autowired
	public CandidatesController(CandidateService candidateService) {
	this.candidateService = candidateService;
}

	@GetMapping("/getByIdentityNumber")
	public DataResult<Candidate> getByIdentityNumber(@RequestParam String identityNumber){
		return this.candidateService.getByIdentityNumber(identityNumber);
	}
	
	@GetMapping("/getById")
	public DataResult<Candidate> getById(@RequestParam int id){
		return this.candidateService.getById(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<Candidate>> getAll(){
		return this.candidateService.getAll();
	}
	
	@PostMapping("/add")
	public DataResult add(@RequestBody Candidate candidate){
		DataResult result = this.candidateService.add(candidate);
		return result;
	}
	@DeleteMapping("/delete")
	public Result delete(int id) {
		return this.candidateService.delete(id);
	}
}
