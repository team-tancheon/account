package com.tancheon.account.dao.repository;

import com.tancheon.account.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByKey(String jwtKey);

    void deletByKey(String jwtKey);
}
