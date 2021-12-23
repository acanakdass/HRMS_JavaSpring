package javaProjects.HRMS.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javaProjects.HRMS.business.abstracts.CloudinaryImageService;

import javaProjects.HRMS.core.utilities.results.DataResult;


@RestController
@RequestMapping("/api/cloudinary")
@CrossOrigin
public class CloudinaryImagesController {
	private CloudinaryImageService cloudinaryService;

	
	@Autowired
	public CloudinaryImagesController(CloudinaryImageService cloudinaryService) {
		super();
		this.cloudinaryService = cloudinaryService;
	}
	



	@PostMapping("/upload")
	public DataResult<String> upload(@RequestParam("file") MultipartFile file, @RequestParam Long userId){
		System.out.println(userId);
		return this.cloudinaryService.uploadImageFile(file,userId);
	}


}
