package com.todolist.todolist.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todolist.Models.Task;
import com.todolist.todolist.Models.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
    List<Task> findAllByUser(User user);

}