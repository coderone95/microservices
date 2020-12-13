package com.coderone95.user.service.controller;

import com.coderone95.user.service.entity.Role;
import com.coderone95.user.service.entity.RolePrivilegesMapping;
import com.coderone95.user.service.exceptions.RoleAlreadyExists;
import com.coderone95.user.service.exceptions.RoleGenericException;
import com.coderone95.user.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/save")
    public ResponseEntity<Role>  save(@Valid @RequestBody Role role){

        if(roleService.isRoleExistsByRole(role)) {
            throw new RoleAlreadyExists("Role type is already exists");
        }

        Role savedRole = roleService.save(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Integer id){
        if(!roleService.isExistsById(id)){
            throw new RoleGenericException("Role is not found");
        }
        Role role = roleService.getRole(id);
        return new ResponseEntity<>(role,HttpStatus.FOUND);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Role>  updateRole(@PathVariable("id") Integer id, @RequestBody Role role){
        if(!roleService.isExistsById(id)){
            throw new RoleGenericException("Invalid Role");
        }
        Role updatedRole = roleService.update(id,role);
        return new ResponseEntity<>(updatedRole,HttpStatus.OK);
    }

    @PostMapping(value = "/addPrivilege/{roleId}")
    public ResponseEntity<RolePrivilegesMapping> savePrivilgesForRole(@PathVariable("roleId") Long roleId,
                 @Valid @RequestBody RolePrivilegesMapping bo){
        if(roleService.isFunctionalityExistsForRole(roleId,bo.getFunctionalityId())){
            throw new RoleGenericException("Functionality already exists for this role id");
        }
        RolePrivilegesMapping data = roleService.saveRolePrivileges(bo);
        return new ResponseEntity<>(data,HttpStatus.CREATED);
    }

}
