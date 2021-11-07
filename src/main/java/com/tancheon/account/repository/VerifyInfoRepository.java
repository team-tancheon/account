package com.tancheon.account.repository;

import com.tancheon.account.domain.VerifyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyInfoRepository extends JpaRepository<VerifyInfo, String> {
}
