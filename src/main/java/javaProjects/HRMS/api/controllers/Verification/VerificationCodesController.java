package javaProjects.HRMS.api.controllers.Verification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaProjects.HRMS.business.abstracts.Verification.VerificationCodeService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.abstracts.VerificationCode;
import javaProjects.HRMS.entities.concretes.Users.Candidate;

@RestController
@RequestMapping("/api/verificationCodes")
@CrossOrigin
public class VerificationCodesController {
	
	private VerificationCodeService verificationCodeService;

	@Autowired
	public VerificationCodesController(VerificationCodeService verificationCodeService) {
		this.verificationCodeService = verificationCodeService;
	}
	
	@GetMapping("/getById")
	public DataResult<VerificationCode> getById(@RequestParam int id){
		return this.verificationCodeService.getById(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<VerificationCode>> getAll(){
		return this.verificationCodeService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody VerificationCode verificationCode){
		Result result = this.verificationCodeService.add(verificationCode);
		return result;
	}

}
