package org.twspring.lab10.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.lab10.Model.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
}
