package com.coderone95.user.service.controller;

import com.coderone95.user.service.entity.User;
import com.coderone95.user.service.exceptions.UserGenericException;
import com.coderone95.user.service.model.Status;
import com.coderone95.user.service.model.SuccessResponse;
import com.coderone95.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        User savedData = userService.save(user);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        if(!userService.isUserExistsById(id)){
            throw new UserGenericException("User not found");
        }
        User user = userService.getUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        if(!userService.isUserExistsById(id)){
            throw new UserGenericException("No such User");
        }
        User data = userService.updateUser(id, user);
        return  new ResponseEntity<>(data, HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        if(!userService.isUserExistsById(id)){
            throw new UserGenericException("No such User");
        }
        try{
            userService.deleteUserById(id);
            SuccessResponse res = new SuccessResponse();
            res.setMessage("User is deleted");
            res.setStatus(new Status());
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){

        }
        return  null;

    }



}
