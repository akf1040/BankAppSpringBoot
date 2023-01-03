package com.outsource.bankappspringboot.repository;

import com.outsource.bankappspringboot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
