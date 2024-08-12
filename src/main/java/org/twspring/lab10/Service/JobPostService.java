package org.twspring.lab10.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab10.Model.JobPost;
import org.twspring.lab10.Repository.JobPostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }

    //update
    public boolean updateJobPost(Integer id, JobPost jobPost) {
        if(jobPostRepository.findById(id).isEmpty()){
            return false;
        }
        JobPost updatedJobPost = jobPostRepository.getById(id);
        updatedJobPost.setTile(jobPost.getTile());
        updatedJobPost.setDescription(jobPost.getDescription());
        updatedJobPost.setSalary(jobPost.getSalary());
        updatedJobPost.setLocation(jobPost.getLocation());
        updatedJobPost.setHireDate(jobPost.getHireDate());
        jobPostRepository.save(updatedJobPost);
        return true;
    }

    //delete
    public boolean deleteJobPost(Integer id) {
        if(jobPostRepository.findById(id).isEmpty()){
            return false;
        }
        jobPostRepository.deleteById(id);
        return true;
    }

}
