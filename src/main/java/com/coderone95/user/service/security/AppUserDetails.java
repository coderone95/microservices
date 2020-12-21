package com.coderone95.user.service.security;

import com.coderone95.user.service.entity.Role;
import com.coderone95.user.service.entity.UserLoginData;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AppUserDetails {
        //implements UserDetails {

//    private static final long serialVersionUID = 6529685098267757690L;
//
//    private String userName;
//    private String password;
//    private boolean active;
//    private List<GrantedAuthority> authorityList;
//
//    public AppUserDetails(){}
//
//    public AppUserDetails(UserLoginData user, Role role){
//        this.userName= user.getLoginId();
//        this.password = user.getPassword();
//        this.active = user.getDisabled();
//        this.authorityList = Arrays.asList(new SimpleGrantedAuthority(role.getRoleType()));
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorityList;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
