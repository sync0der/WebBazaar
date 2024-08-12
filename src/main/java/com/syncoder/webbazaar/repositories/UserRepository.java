package com.syncoder.webbazaar.repositories;

import com.syncoder.webbazaar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
