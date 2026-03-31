package com.todolist.todolist.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.todolist.DTOs.TaskResponseDto;
import com.todolist.todolist.Models.Task;
import com.todolist.todolist.Models.User;
import com.todolist.todolist.Services.TaskService;
import com.todolist.todolist.Services.UserService;


@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // GETS

    @GetMapping("/user/{id}")//METODO QUE RETORNA TODAS AS TASKS DE UM DETERMINADO USUARIO POR UM ID
    public ResponseEntity<List<Task>> findAllByUser(@PathVariable Long id){
        User user = this.userService.findById(id);
        List<Task> tasks = this.taskService.findAllByUser(user);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("")//METODO QUE RETORNA TODAS AS TASKS DO BANCO DE DADOS
    public ResponseEntity<List<Task>> findAll () {
        return ResponseEntity.ok(this.taskService.findAll());
    }

    @GetMapping("/{id}")//METODO PARA ENCONTRAR UMA DETERMINDADA TASK PELO SEU ID
    public ResponseEntity<Task> findById (@PathVariable Long id){
        return ResponseEntity.ok(this.taskService.findById(id));
    }

    // POSTS

    @PostMapping("")//METODO PARA CRIAR UMA NOVA TASK
    public ResponseEntity<TaskResponseDto> create(@RequestBody Task task) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//FAZ COM QUE O USER SEJA O USUARIO DO EMAIL QUE ENVIOU O TOKEN
    task.setUser(user);

    Task savedTask = taskService.create(task);
    TaskResponseDto dto = new TaskResponseDto();

    dto.setId(savedTask.getId());
    dto.setName(savedTask.getName());
    dto.setDescription(savedTask.getDescription());
    dto.setCompleted(savedTask.isCompleted());
    dto.setCreationDate(savedTask.getCreationDate());
    dto.setDueDate(savedTask.getDueDate());
    dto.setUserId(user.getId());

    return ResponseEntity.ok(dto);
    }

    // PUTS

    @PutMapping("/{id}")
    public ResponseEntity<Task> update (@RequestBody Task task, @PathVariable Long id){
        return ResponseEntity.ok(this.taskService.update(task, id));
    }

    // DELETES

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById (@PathVariable Long id){
        this.taskService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}