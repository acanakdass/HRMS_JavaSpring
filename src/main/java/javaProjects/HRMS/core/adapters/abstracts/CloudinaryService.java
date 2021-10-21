package javaProjects.HRMS.core.adapters.abstracts;



import org.springframework.web.multipart.MultipartFile;

import javaProjects.HRMS.core.utilities.results.DataResult;

public interface CloudinaryService {
	DataResult<String> uploadImageFile(MultipartFile file);
}
