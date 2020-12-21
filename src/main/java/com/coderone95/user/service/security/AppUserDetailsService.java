package com.coderone95.user.service.security;

import com.coderone95.user.service.entity.Role;
import com.coderone95.user.service.entity.UserLoginData;
import com.coderone95.user.service.service.RoleService;
import com.coderone95.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService {
    //implements
//} UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserLoginData userLoginData = userService.getUserLoginDataByUserName(username);
//        if(userLoginData == null){
//            throw new UsernameNotFoundException("Invalid Credentials");
//        }
//        System.out.println("UserLogin Data "+ userLoginData.getUser().getRoleId().intValue());
//
//        Role role = roleService.getRole(userLoginData.getUser().getRoleId().intValue());
//        System.out.println("Role "+role.getRoleType());
//        return new AppUserDetails(userLoginData,role);
//    }
}
