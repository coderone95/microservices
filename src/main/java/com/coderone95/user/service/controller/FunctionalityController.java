package com.coderone95.user.service.controller;

import com.coderone95.user.service.entity.FunctionalityMapping;
import com.coderone95.user.service.entity.FunctionalityMst;
import com.coderone95.user.service.entity.User;
import com.coderone95.user.service.exceptions.RoleGenericException;
import com.coderone95.user.service.model.ErrorResponse;
import com.coderone95.user.service.model.SuccessResponse;
import com.coderone95.user.service.service.RoleService;
import com.coderone95.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/functionality")
public class FunctionalityController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewFunctionality(@Valid @RequestBody FunctionalityMst fun){
        FunctionalityMst data = roleService.addNewFunctionality(fun);
        if(data == null){
            return new ResponseEntity("Error while adding new functionality", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Added new functionality", HttpStatus.CREATED);
    }

    @PutMapping(value = "/updatePrivilege/{userId}")
    public ResponseEntity<?> updateUserPrvilige(@PathVariable("userId") Long userId,
                   @Valid @RequestBody FunctionalityMapping bo){
        if(!userService.isUserExistsById(userId)){
            throw new RoleGenericException("Invalid user id");
        }
        if(!roleService.isFunctionalityExistsForRoleAndUserId(bo.getRoleId(),bo.getFunctionalityId(), userId)){
            throw new RoleGenericException("Invalid data. Please check with functionalityId or roleId");
        }
        bo.setUser(userService.getUser(userId));
        FunctionalityMapping data = roleService.updateUserPrivilege(bo);
        return new ResponseEntity(data, HttpStatus.ACCEPTED);
    }



}
