package com.coderone95.user.service.repository;

import com.coderone95.user.service.entity.RolePrivilegesMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePrivilegesMappingRepository extends JpaRepository<RolePrivilegesMapping, Long> {


    RolePrivilegesMapping findByRoleIdAndFunctionalityId(Long roleId, Long functionalityId);
    List<RolePrivilegesMapping> findByRoleId(Long roleId);

}
