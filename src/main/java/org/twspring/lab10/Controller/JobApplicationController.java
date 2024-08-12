package org.twspring.lab10.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab10.Api.ApiResponse;
import org.twspring.lab10.Model.JobApplication;
import org.twspring.lab10.Service.JobApplicationService;

import java.util.List;

@RestController
@RequestMapping("api/v1/job_application")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllJobApplications(){
        if(jobApplicationService.getAllJobApplications().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No job applications found"));
        }
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/apply/{userId}/{jobPostId}")
    public ResponseEntity applyForJobPost(@PathVariable Integer userId, @PathVariable Integer jobPostId){
        int flag = jobApplicationService.applyForJobPost(userId, jobPostId);

        switch (flag){
            case 0:
                return ResponseEntity.status(201).body(new ApiResponse("Job application successfully applied"));
                case 1:
                    return ResponseEntity.status(409).body(new ApiResponse("Job application already exists"));
                    case 2:
                        return ResponseEntity.status(400).body(new ApiResponse("User doesn't exist"));
                        case 3:
                            return ResponseEntity.status(409).body(new ApiResponse("Only job seekers can apply for jobs"));
                            case 4:
                                return ResponseEntity.status(409).body(new ApiResponse("Job post doesn't exist"));
            default:
                return ResponseEntity.status(400).body(new ApiResponse("An error occurred"));

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id){
        if (jobApplicationService.deleteApplication(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Job application successfully deleted"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Job application not found"));
    }

}
