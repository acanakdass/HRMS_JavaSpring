package javaProjects.HRMS.entities.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementAddDto {
	
	private Long id;
		
	private String description;

	private int quota;
	
	private int minSalary;
	
	private int maxSalary;
	
	private boolean isActive;

    private Date expirationDate;

//    private Date releaseDate;
 
	private int cityId;
	

	private int jobTitleId;

    private int employerId;
    
    private int workTypeId;

    private int workTimeId;
}
