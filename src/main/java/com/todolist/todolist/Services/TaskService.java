package com.todolist.todolist.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todolist.Models.Task;
import com.todolist.todolist.Models.User;
import com.todolist.todolist.Repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    @Transactional
    public Task findById(Long id){
        return this.taskRepository.findById(id).orElseThrow(
            ()->new RuntimeException("Não foi possivel encontrar o usuario."));
    }

    @Transactional
    public Task create(Task task){
        return this.taskRepository.save(task);
    }

    @Transactional
    public void deleteById(Long id){
        this.taskRepository.deleteById(id);
    }

    @Transactional
    public Task update(Task task, Long id){
        Task newTask = this.taskRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Não foi possivel encontrar o usuario"));
        
        newTask.setName(task.getName());
        newTask.setDescription(task.getDescription());

        return taskRepository.save(newTask);
    }

    //Função que retorna todas as tasks de um determinado usuario
    @Transactional
    public List<Task> findAllByUser(User user){
        return this.taskRepository.findAllByUser(user);
    }

}