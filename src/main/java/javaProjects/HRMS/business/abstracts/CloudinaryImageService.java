package javaProjects.HRMS.business.abstracts;


import org.springframework.web.multipart.MultipartFile;

import javaProjects.HRMS.core.utilities.results.DataResult;

public interface CloudinaryImageService {
	DataResult<String> uploadImageFile(MultipartFile file,int userId);
}
