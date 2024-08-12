package org.twspring.lab10.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab10.Model.JobApplication;
import org.twspring.lab10.Repository.JobPostRepository;
import org.twspring.lab10.Repository.JopApplicationRepository;
import org.twspring.lab10.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JopApplicationService {
    private JopApplicationRepository jopApplicationRepository;
    private UserRepository userRepository;
    private JobPostRepository JobpostRepository;

    //get
    public List<JobApplication> getAllApplications() {
        return jopApplicationRepository.findAll();
    }

    //user applies for a job post
    public int applyForJobPost(Integer userId, Integer jobPostId){
        if(userRepository.findById(userId).isEmpty()){
            return 1; //user doesn't exist
        }
        if(JobpostRepository.findById(jobPostId).isEmpty()){
            return 2; //job post doesn't exist
        }
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobPostId(jobPostId);
        jobApplication.setUserId(userId);
        jopApplicationRepository.save(jobApplication);
        return 0; //success
    }

    //user withdraws from job application
    public boolean deleteApplication(Integer userId, Integer applicationId){
        if(jopApplicationRepository.findById(applicationId).isEmpty()){
            return false;
        }
        jopApplicationRepository.deleteById(applicationId);
        return true;
    }
}
