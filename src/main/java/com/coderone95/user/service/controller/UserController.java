package com.coderone95.user.service.controller;

import com.coderone95.user.service.entity.User;
import com.coderone95.user.service.entity.UserLoginData;
import com.coderone95.user.service.exceptions.UserGenericException;
import com.coderone95.user.service.model.ErrorResponse;
import com.coderone95.user.service.model.Status;
import com.coderone95.user.service.model.SuccessResponse;
import com.coderone95.user.service.service.UserService;
import com.coderone95.user.service.utility.StringUtility;
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

    @PostMapping(value = {"/save","/register"})
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
            SuccessResponse res = new SuccessResponse("User is deleted", "SUCCESS");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            ErrorResponse err = new ErrorResponse("ERROR while deleting user", "ERROR");
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> getUserByLoginId(@RequestParam("loginId") String loginId){
        UserLoginData user = new UserLoginData();
        try{
            user = userService.getUserByLoginId(loginId);
        }catch (Exception e){
            ErrorResponse err = new ErrorResponse("ERROR while getting the user login data by login id", "ERROR");
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> enableOrDisabledUserId(@RequestParam("id") Long id, @RequestParam("flag") String flag){
        if(userService.isUserExistsById(id)){
            if(flag != null && !flag.isEmpty()){
                userService.updateUser(id, flag);
            }else{
                throw new UserGenericException("Provide operation type");
            }
        }else{
            throw new UserGenericException("Invalid User id");
        }

        SuccessResponse res = new SuccessResponse("User is "+flag,"SUCCESS");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping(value="/password/reset")
    public ResponseEntity<?> resetPassword(@RequestParam("loginId") String loginId,
               @RequestParam("new_password") String newPassword,
               @RequestParam("confirm_password") String confirmPassword){
        if(StringUtility.isNotNullAndBlank(newPassword) &&
            StringUtility.isNotNullAndBlank(confirmPassword)){
            if(newPassword.equals(confirmPassword) && userService.isUserExistsByLoginId(loginId)){
                userService.updateUserPassword(loginId,newPassword);
            }else if(!userService.isUserExistsByLoginId(loginId)){
                throw new UserGenericException("Invalid login Id");
            }else{
                throw new UserGenericException("Password does not match");
            }
        }
        SuccessResponse res = new SuccessResponse("Password Reset successfully","SUCCESS");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/login/validate")
    public ResponseEntity<?> validateLogin(@RequestParam("loginId") String loginId,
           @RequestParam("password") String password){
        if(!userService.isValidLoginCred(loginId,password)){
            throw new UserGenericException("Invalid Username Or Password");
        }
        if(userService.isValidLoginCred(loginId,password)) {
            if(userService.isUserIsDisabled(loginId)){
                throw new UserGenericException("User is locked");
            }
        }
        SuccessResponse res = new SuccessResponse("Valid User","SUCCESS");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
