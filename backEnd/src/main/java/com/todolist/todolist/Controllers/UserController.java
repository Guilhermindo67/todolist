package com.todolist.todolist.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.todolist.Models.User;
import com.todolist.todolist.Services.UserService;

import org.springframework.web.bind.annotation.RequestParam;

import com.todolist.todolist.DTOs.EmailDto;
import com.todolist.todolist.Models.Task;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    //GETS

    @GetMapping("")//METODO PARA ENCONTRAR TODOS OS USUARIOS
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());        
    }
    
    @GetMapping("/task")//METODO PARA ENCONTRAR TODAS AS TASKS DE UM DETERMINADO USUARIO PELO EMAIL
    public ResponseEntity<List<Task>> findAllTasksByEmail(@RequestBody EmailDto dto) {
        return ResponseEntity.ok(this.userService.findAllTasksByEmail(dto.getEmail()));
    }
    
    @GetMapping("/{id}")//METODO PARAR ENCONTRAR UM USUARIO PELO ID 
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    //POSTS
    
    @PostMapping("")//METODO PARA CRIAR UM USUARIO SEM NENHUM TIPO DE VALIDAÇÃO
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.create(user));
    }
    
    //PUTS

    @PutMapping("/{id}")//METODO PARA ATUALIZAR OS DADOS DE UM USUARIO
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(this.userService.update(user, id));
    }

    //DELETES

    @DeleteMapping("/{id}")//METODO PARA DELETAR UM USUARIO PELO ID
    public ResponseEntity<Object> deleteByid(@PathVariable Long id){
        this.userService.deleteById(id);
        return ResponseEntity.accepted().build();
    }


}