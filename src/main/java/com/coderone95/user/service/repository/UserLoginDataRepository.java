package com.coderone95.user.service.repository;

import com.coderone95.user.service.entity.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserLoginDataRepository extends JpaRepository<UserLoginData,Long> {
    Optional<UserLoginData> findByLoginId(String username);
}
