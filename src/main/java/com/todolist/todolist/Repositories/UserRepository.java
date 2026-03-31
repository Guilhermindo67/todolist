package com.todolist.todolist.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todolist.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

Optional<User> findByEmail(String email);

}