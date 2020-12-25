package com.coderone95.user.service.service;

import com.coderone95.user.service.entity.FunctionalityMapping;
import com.coderone95.user.service.entity.RolePrivilegesMapping;
import com.coderone95.user.service.entity.User;
import com.coderone95.user.service.entity.UserLoginData;
import com.coderone95.user.service.repository.FunctionalityMappingRepository;
import com.coderone95.user.service.repository.RolePrivilegesMappingRepository;
import com.coderone95.user.service.repository.UserLoginDataRepository;
import com.coderone95.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FunctionalityMappingRepository functionalityMappingRepository;

    @Autowired
    private RolePrivilegesMappingRepository rolePrivilegesMappingRepository;

    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    public User save(User user) {
        if(user.getCreatedOn() == null){
            user.setCreatedOn(LocalDateTime.now());
        }
        if(user.getUpdatedOn() == null){
            user.setUpdatedOn(LocalDateTime.now());
        }
        if(user.getCreatedBy() == null){
            user.setCreatedBy(user.getEmailId());
        }
        if(user.getUpdatedBy() == null){
            user.setUpdatedBy(user.getEmailId());
        }
        UserLoginData userLoginData  = new UserLoginData(user.getEmailId(), user.getUserLoginData().getPassword(),
                user.getEmailId(), user.getEmailId());
        user.setUserLoginData(userLoginData);
        userLoginData.setUser(user);
        List<RolePrivilegesMapping> userPrivilges = getDefualtPrilvelgesForRole(user.getRoleId());
        List<FunctionalityMapping> privileges = userPrivilges.stream().map(obj->{
            FunctionalityMapping functionality = new FunctionalityMapping();
            functionality.setPrivilege(obj.getPrivileges());
            functionality.setRoleId(obj.getRoleId());
            functionality.setFunctionalityId(obj.getFunctionalityId());
            functionality.setUser(user);
            return functionality;
        }).collect(Collectors.toList());
        user.setPrivileges(privileges);

        return userRepository.save(user);
    }

    private List<RolePrivilegesMapping> getDefualtPrilvelgesForRole(Long roleId) {
        return rolePrivilegesMappingRepository.findByRoleId(roleId);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public boolean isUserExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public User updateUser(Long id, User user) {
        User data = userRepository.findById(id).get();
        data.setUpdatedOn(LocalDateTime.now());
        data.setUserName(user.getUserName());
        data.setPhone(user.getPhone());
        return userRepository.save(data);
    }

    public void updateUser(Long id, String flag){
        User user = userRepository.findById(id).get();
        UserLoginData userLoginData  = user.getUserLoginData();
        if(flag.equalsIgnoreCase("ENABLE")){
            userLoginData.setDisabled(false);
        }else{
            userLoginData.setDisabled(true);
        }
        userLoginData.setUpdatedOn(LocalDateTime.now());
        userLoginData.getUser().setUpdatedOn(LocalDateTime.now());
        userLoginDataRepository.save(userLoginData);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserLoginData getUserLoginDataByUserName(String username) {
        Optional<UserLoginData> userLoginData = userLoginDataRepository.findByLoginId(username);
        return userLoginData.get();
    }

    public UserLoginData getUserByLoginId(String loginId) {
        UserLoginData loginData = userLoginDataRepository.findByLoginId(loginId).get();
        return loginData;
    }

    public boolean isUserExistsByLoginId(String loginId) {
        UserLoginData loginData = userLoginDataRepository.getUserByLoginId(loginId);
        if(loginData == null){
            return false;
        }
        return true;
    }

    public void updateUserPassword(String loginId, String newPassword) {
        UserLoginData loginData = getUserByLoginId(loginId);
        loginData.setUpdatedOn(LocalDateTime.now());
        loginData.getUser().setUpdatedOn(LocalDateTime.now());
        userRepository.save(loginData.getUser());
    }

    public boolean isValidLoginCred(String loginId, String password) {
        UserLoginData userLoginData = userLoginDataRepository.findByLoginIdAndPassword(loginId,password);
        if(userLoginData == null){
            return  false;
        }
        return true;
    }

    public boolean isUserIsDisabled(String loginId) {
        UserLoginData userLoginData = getUserByLoginId(loginId);
        if(userLoginData.getDisabled()){
            return true;
        }
        return false;
    }
}
