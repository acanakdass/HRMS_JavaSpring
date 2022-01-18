package javaProjects.HRMS.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.concretes.Users.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="applications")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Application {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnoreProperties(value = "applications")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "jobadvert_id")
    private JobAdvertisement jobAdvert;

    private Date date;

}
