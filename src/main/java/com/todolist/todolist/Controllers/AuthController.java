package com.todolist.todolist.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.todolist.DTOs.LoginRequestDto;
import com.todolist.todolist.DTOs.RegisterRequestDto;
import com.todolist.todolist.DTOs.RegisterResponseDto;
import com.todolist.todolist.Models.User;
import com.todolist.todolist.Repositories.UserRepository;
import com.todolist.todolist.Services.TokenService;
import com.todolist.todolist.Services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto login){
        User user = this.userService.findByEmail(login.email());

        if(passwordEncoder.matches(login.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok("Login efetuado com sucesso! " + token);
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto request){
        
        if(request != null){
            User user = new User();
            user.setName(request.name());
            user.setEmail(request.email());
            user.setPassword(this.passwordEncoder.encode(request.password()));

            String token = this.tokenService.generateToken(user);

            this.userRepository.save(user);

            return ResponseEntity.ok(new RegisterResponseDto(user.toString(), token));
        }
        
        return ResponseEntity.badRequest().build();

    }

}
