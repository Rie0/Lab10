package org.twspring.lab10.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab10.Model.JobApplication;
import org.twspring.lab10.Repository.JobPostRepository;
import org.twspring.lab10.Repository.JobApplicationRepository;
import org.twspring.lab10.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository JobpostRepository;

    //get
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    //user applies for a job post
    public int applyForJobPost(Integer userId, Integer jobPostId){
        for(JobApplication jobApplication : getAllJobApplications()){
            if(jobApplication.getJobPostId().equals(jobPostId)&&jobApplication.getUserId().equals(userId)){
                return 1; //job application already exists
            }
        }
        if(userRepository.findById(userId).isEmpty()){
            return 2; //user doesn't exist
        }
        if(!userRepository.getById(userId).getRole().equals("JOB_SEEKER")){
            return 3; //only job seekers can apply for jobs
        }
        if(JobpostRepository.findById(jobPostId).isEmpty()){
            return 4; //job post doesn't exist
        }
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobPostId(jobPostId);
        jobApplication.setUserId(userId);
        jobApplicationRepository.save(jobApplication);
        return 0; //success
    }

    //user withdraws from job application
    public boolean deleteApplication(Integer id){
        if(jobApplicationRepository.findById(id).isEmpty()){
            return false;
        }
        jobApplicationRepository.deleteById(id);
        return true;
    }
}
