package com.gorkem.auth_service.repos;

import com.gorkem.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}