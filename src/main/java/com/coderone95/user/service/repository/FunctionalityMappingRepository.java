package com.coderone95.user.service.repository;

import com.coderone95.user.service.entity.FunctionalityMapping;
import com.coderone95.user.service.entity.RolePrivilegesMapping;
import com.coderone95.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionalityMappingRepository extends JpaRepository<FunctionalityMapping,Long> {
    //List<RolePrivilegesMapping> findByRoleId(Long roleId);

    @Query(nativeQuery = true,value = "SELECT * from tbl_functionality_mapping f WHERE f.user_id=:userId AND f.functionality_id=:funId AND f.role_id=:roleId")
    FunctionalityMapping findByRoleIdAndFunctionalityIdAndUserId(@Param("roleId")Long roleId,
             @Param("funId")Long functionalityId,
             @Param("userId") Long userId);
}
