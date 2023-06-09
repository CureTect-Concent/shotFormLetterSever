package com.shotFormLetter.sFL.domain.user.controller;

//import com.shotFormLetter.sFL.domain.user.domain.form.LoginForm;
import com.shotFormLetter.sFL.domain.user.domain.form.UserCreateForm;
import com.shotFormLetter.sFL.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid form data", HttpStatus.BAD_REQUEST);
        }


        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getUser_nickname(), userCreateForm.getPassword());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody @Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
//        try {
//            userService.create(userCreateForm.getUsername(), userCreateForm.getUser_nickname(), userCreateForm.getPassword1());
//        } catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
//    }
//    public ResponseEntity<?> signup(@RequestBody Map<String, String> request, @Valid UserCreateForm userCreateForm) {
//        String username = request.get("username");
//        String user_nickname = request.get("user_nickname");
//        String password1 = request.get("password1");
//
//        try {
//            userService.create(userCreateForm.getUsername(),userCreateForm.getUser_nickname(), userCreateForm.getPassword1());
//        } catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
//    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>("Invalid form data", HttpStatus.BAD_REQUEST);
//        }
//
//        // Implement your login logic here
//        // If login is successful, return a success message
//        // If login fails, return an error message
//
//        return new ResponseEntity<>("Login form", HttpStatus.OK);
//    }
}
