package com.todolist.todolist.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todolist.Models.Task;
import com.todolist.todolist.Models.User;
import com.todolist.todolist.Repositories.TaskRepository;
import com.todolist.todolist.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    @Transactional
    public User findById(Long id){
        return this.userRepository.findById(id).orElseThrow(
            ()->new RuntimeException("Não foi possivel encontrar o usuario."));
    }

    @Transactional
    public User findByEmail(String email){
        return this.userRepository.findByEmail(email).orElseThrow(
            () ->new RuntimeException("Não foi possivel encontrar o usuario"));
    }

    @Transactional
    public User create(User user){
        return this.userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id){
        this.userRepository.deleteById(id);
    }

    @Transactional
    public User update(User user, Long id){
        User newUser = this.userRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Não foi possivel encontrar o usuario"));
        
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return userRepository.save(newUser);
    }

    @Transactional
    public List<Task> findAllTasksByEmail(String email){
        User user = this.findByEmail(email);
        return taskRepository.findAllByUser(user);
    }

}