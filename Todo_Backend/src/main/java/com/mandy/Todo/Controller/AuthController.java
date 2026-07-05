package com.mandy.Todo.Controller;

import com.mandy.Todo.Models.user;
import com.mandy.Todo.Respository.userrepository;
import com.mandy.Todo.service.userservice;
import com.mandy.Todo.utils.utilvalid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder obp;
    private final userrepository ob;
    private final userservice obs;
    private final utilvalid jwt;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registeruser(@RequestBody Map<String,String> body) {

        String email = body.get("email");
        String password = body.get("password");
        password=passwordEncoder.encode(password);
        if (ob.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("User already Found", HttpStatus.OK);
        }
        obs.createTodo(user.builder().email(email).password(password).build());
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginuser(@RequestBody Map<String,String> body) {

        String email = body.get("email");
        String password = body.get("password");
        var useroptional =ob.findByEmail(email);
        if (useroptional.isEmpty()) {
            return new ResponseEntity<>("Email not exists", HttpStatus.UNAUTHORIZED);
        }
        user obu=useroptional.get();
        if(!obp.matches(password,obu.getPassword()))
        {
            return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);
        }
        String token=jwt.generatetokens(email);
        return ResponseEntity.ok(Map.of("token",token));

        //return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }
}