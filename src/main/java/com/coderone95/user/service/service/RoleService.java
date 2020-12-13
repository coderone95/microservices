package com.coderone95.user.service.service;

import com.coderone95.user.service.entity.*;
import com.coderone95.user.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("roleService")
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolePrivilegesMappingRepository rolePrivilegesMappingRepository;

    @Autowired
    private FunctionalityMappingRepository functionalityMappingRepository;

    @Autowired
    private FunctionalityMstRepository functionMstRepo;

    public Role save(Role role) {
        return  roleRepository.save(role);
    }

    public boolean isRoleExistsByRole(Role role) {
        Role data = roleRepository.findByRoleType(role.getRoleType());
        if(data == null){
            return false;
        }
        return true;
    }

    public boolean isExistsById(Integer id) {
        return roleRepository.existsById(id);
    }

    public Role getRole(Integer id) {
        return roleRepository.findById(id).get();
    }

    public Role update(Integer id, Role role) {
        Role data = roleRepository.findById(id).get();
        data.setRoleType(role.getRoleType());
        return roleRepository.save(data);
    }

    public FunctionalityMst addNewFunctionality(FunctionalityMst fun) {
       return functionMstRepo.save(fun);
    }

    public boolean isFunctionalityExistsForRole(Long roleId, Long functionalityId) {
        RolePrivilegesMapping data = rolePrivilegesMappingRepository.
                findByRoleIdAndFunctionalityId(roleId,functionalityId);
        if(data !=null){
            return  true;
        }
        return false;
    }

    public RolePrivilegesMapping saveRolePrivileges(RolePrivilegesMapping bo) {
        return rolePrivilegesMappingRepository.save(bo);
    }

    public boolean isFunctionalityExistsForRoleAndUserId(Long roleId, Long functionalityId, Long userId) {
        User user = userRepository.findById(userId).get();
        if(user == null){
            return false;
        }
        FunctionalityMapping data = functionalityMappingRepository.
                findByRoleIdAndFunctionalityIdAndUserId(roleId,functionalityId,user.getId());
        if(data !=null){
            return  true;
        }
        return false;
    }

    public FunctionalityMapping updateUserPrivilege(FunctionalityMapping bo) {
        FunctionalityMapping data = getFunctionalityBoForRoleAndFunctionalityAndUser(bo.getRoleId(),
                bo.getFunctionalityId(), bo.getUser());
        if(data !=null){
            data.setPrivilege(bo.getPrivilege());
            functionalityMappingRepository.save(data);
        }
        return data;
    }

    public FunctionalityMapping getFunctionalityBoForRoleAndFunctionalityAndUser(Long roleId, Long functionalityId, User user){
        FunctionalityMapping data = functionalityMappingRepository.
                findByRoleIdAndFunctionalityIdAndUserId(roleId,functionalityId,user.getId());
        return data;
    }
}
