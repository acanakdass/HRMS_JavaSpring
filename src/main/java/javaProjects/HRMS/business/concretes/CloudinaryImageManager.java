package javaProjects.HRMS.business.concretes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javaProjects.HRMS.business.abstracts.CloudinaryImageService;
import javaProjects.HRMS.core.business.abstracts.UserService;
import javaProjects.HRMS.core.adapters.abstracts.CloudinaryService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.entities.User;

@Service
@Slf4j
public class CloudinaryImageManager implements CloudinaryImageService {

	private final CloudinaryService cloudinaryService;
	
	private final UserService userService;
	
	@Autowired
	public CloudinaryImageManager(CloudinaryService cloudinaryService, UserService userService) {
		this.cloudinaryService = cloudinaryService;
		this.userService = userService;
	}
	

	@Override
	public DataResult<String> uploadImageFile(MultipartFile file, Integer userId) {
		DataResult<String> uploadResult = this.cloudinaryService.uploadImageFile(file);
		log.info("Uploading image for user with id : {}",userId);
		String addedPhotoUrl = uploadResult.getData();
		var user =  this.userService.findById(userId);

		if(user.isSuccess()) {
			user.getData().setPhotoUrl(addedPhotoUrl);
			log.info("Saving user with new photo url");
			userService.saveUser(user.getData());
		}else {
				return new ErrorDataResult<>("User Not Found");
		}

		return uploadResult;
	}

}
