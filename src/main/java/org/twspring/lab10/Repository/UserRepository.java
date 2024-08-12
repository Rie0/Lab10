package org.twspring.lab10.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.lab10.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
