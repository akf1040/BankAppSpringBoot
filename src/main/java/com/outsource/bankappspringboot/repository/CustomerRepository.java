package com.outsource.bankappspringboot.repository;

import com.outsource.bankappspringboot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
