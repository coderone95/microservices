package com.coderone95.user.service.repository;

import com.coderone95.user.service.entity.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserLoginDataRepository extends JpaRepository<UserLoginData,Long> {
    Optional<UserLoginData> findByLoginId(String username);

//    @Query(value = "SELECT u FROM UserLoginData u WHERE u.loginId = ?1")
    @Query(value = "SELECT u FROM UserLoginData u WHERE u.loginId =:loginId")
    UserLoginData getUserByLoginId(@Param("loginId") String loginId);

    UserLoginData findByLoginIdAndPassword(String loginId, String password);


}
