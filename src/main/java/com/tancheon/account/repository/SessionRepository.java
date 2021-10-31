package com.tancheon.account.repository;

import com.tancheon.account.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {

}
