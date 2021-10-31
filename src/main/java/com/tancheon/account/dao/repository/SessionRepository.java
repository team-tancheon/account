package com.tancheon.account.dao.repository;

import com.tancheon.account.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {

}
