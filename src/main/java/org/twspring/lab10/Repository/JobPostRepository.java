package org.twspring.lab10.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.lab10.Model.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
}
