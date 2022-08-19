package com.spring.todo.repositories;

import com.spring.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAndPassword(String email, String password);
}
