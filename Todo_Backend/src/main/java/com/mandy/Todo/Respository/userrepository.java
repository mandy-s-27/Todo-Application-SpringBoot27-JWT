package com.mandy.Todo.Respository;

import com.mandy.Todo.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userrepository extends JpaRepository<user,Long> {
    Optional<user> findByEmail(String email);
}
