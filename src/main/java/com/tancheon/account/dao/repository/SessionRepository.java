package com.tancheon.account.dao.repository;

import com.tancheon.account.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findById(String jwtKey);

    void deleteById(String jwtKey);
}
