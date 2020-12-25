package com.coderone95.user.service.repository;

import com.coderone95.user.service.entity.User;
import com.coderone95.user.service.entity.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserLoginData(UserLoginData loginData);

    @Query(name = "select * from tbl_users u inner join tbl_role_mst r on r.role_id = u.role_id where r.role_type =:roleType",
            nativeQuery = true)
    List<User> getUsersByRoleType(@RequestParam("roleType") String roleType);
}
