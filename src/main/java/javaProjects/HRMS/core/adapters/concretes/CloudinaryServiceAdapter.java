package javaProjects.HRMS.core.adapters.concretes;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import javaProjects.HRMS.core.adapters.abstracts.CloudinaryService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;

@Service
public class CloudinaryServiceAdapter implements CloudinaryService {
	

	@Autowired
	public CloudinaryServiceAdapter() {
	}
	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "acanakdas", "api_key", "486474869953668",
			"api_secret", "VhiPy8KkCZUXwJ6eSgngQTDhPzU", "secure", true));
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public DataResult<String> uploadImageFile(MultipartFile file) {
		try {

			Map<String, Object> resultMap =  cloudinary.uploader().upload(((MultipartFile) file).getBytes(),
					ObjectUtils.emptyMap());
			return new SuccessDataResult<String>(resultMap.get("secure_url").toString(),"Image successfully uploaded");

		} catch (IOException e) {
			return new ErrorDataResult<>("Error while uploading image: "+e.toString());

		}
	}

}
