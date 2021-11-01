package javaProjects.HRMS.dataAccess.abstracts.JobAdvertisement;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	@Query("From JobAdvertisement where isActive = true")
	List<JobAdvertisement> GetAllActive();

	@Query("From JobAdvertisement where isActive=true ORDER BY releaseDate ASC")
	List<JobAdvertisement> getAllActiveByAscReleaseDate();
	
	@Query("From JobAdvertisement where isActive=true ORDER BY releaseDate DESC")
	List<JobAdvertisement> getAllActiveByDescReleaseDate();
	
	@Query("From JobAdvertisement where employer.companyName=:companyName AND isActive=true")
	List<JobAdvertisement> getAllActiveByCompanyName(String companyName);
	
	@Query("From JobAdvertisement where isActive = true AND systemEmployeeConfirm.isConfirmed=true")
	List<JobAdvertisement> GetAllActiveAndConfirmed();

	@Query("From JobAdvertisement where isActive = true AND systemEmployeeConfirm.isConfirmed=true ORDER BY releaseDate ASC")
	List<JobAdvertisement> GetAllActiveAndConfirmed(Pageable pageable);
}
