package javaProjects.HRMS.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javaProjects.HRMS.business.abstracts.CloudinaryImageService;
import javaProjects.HRMS.business.abstracts.UserService;
import javaProjects.HRMS.core.adapters.abstracts.CloudinaryService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.entities.abstracts.User;

@Service
public class CloudinaryImageManager implements CloudinaryImageService {

	private CloudinaryService cloudinaryService;
	
	private UserService userService;
	
	@Autowired
	public CloudinaryImageManager(CloudinaryService cloudinaryService, UserService userService) {
		this.cloudinaryService = cloudinaryService;
		this.userService = userService;
	}
	

	@Override
	public DataResult<String> uploadImageFile(MultipartFile file, int userId) {
		DataResult<String> uploadResult = this.cloudinaryService.uploadImageFile(file);
		String addedPhotoUrl = uploadResult.getData();
		DataResult<User> user =  this.userService.getById(userId);
		if(user.isSuccess()) {
			user.getData().setPhotoUrl(addedPhotoUrl);
			//Updates user if already exists
			this.userService.add(user.getData());			
		}else {
				return new ErrorDataResult<>("User Not Found");
		}
		
		return uploadResult;
	}

}
