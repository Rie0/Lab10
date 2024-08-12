package org.twspring.lab10.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab10.Api.ApiResponse;
import org.twspring.lab10.Model.JobPost;
import org.twspring.lab10.Service.JobPostService;

@RestController
@RequestMapping("api/v1/job_post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        if (jobPostService.getAllJobPosts().isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No Job Posts Found"));
        }
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(201).body(new ApiResponse("Job Post Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @Valid @RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (jobPostService.updateJobPost(id, jobPost)) {
            return ResponseEntity.status(201).body(new ApiResponse("Job Post Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("No Job Post Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        if (jobPostService.deleteJobPost(id)) {
            return ResponseEntity.status(201).body(new ApiResponse("Job Post Deleted Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("No Job Post Found"));
    }
}
