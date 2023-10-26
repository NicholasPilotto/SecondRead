package com.nicholaspilotto.userservice.repositories;

import com.nicholaspilotto.userservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findOne(long id);
}
