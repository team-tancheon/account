package com.tancheon.account.data.repository;

import com.tancheon.account.data.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
