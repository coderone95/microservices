package com.coderone95.user.service.repository;

import com.coderone95.user.service.entity.FunctionalityMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalityMstRepository extends JpaRepository<FunctionalityMst, Long> {


}
