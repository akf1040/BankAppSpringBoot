package com.outsource.bankappspringboot.service;


import com.outsource.bankappspringboot.dto.AccountDto;
import com.outsource.bankappspringboot.dto.AccountDtoConverter;
import com.outsource.bankappspringboot.dto.CreateAccountRequest;
import com.outsource.bankappspringboot.dto.UpdateAccountRequest;
import com.outsource.bankappspringboot.model.Account;
import com.outsource.bankappspringboot.model.City;
import com.outsource.bankappspringboot.model.Customer;
import com.outsource.bankappspringboot.repository.AccountRepository;
import com.outsource.bankappspringboot.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoConverter accountDtoConverter;
    private final CustomerService customerService;


    public AccountService(AccountRepository accountRepository, AccountDtoConverter accountDtoConverter, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.accountDtoConverter = accountDtoConverter;
        this.customerService = customerService;

    }


    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
                Customer customer=customerService.getCustomerById(createAccountRequest.getCustomerId());
                if(customer.getId().equals(" ") || customer.getId()==null){
                    return AccountDto.builder().build();
                }
                Account account=  Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .city(City.valueOf(createAccountRequest.getCity().name()))
                .curreny(createAccountRequest.getCurreny())
                .customerId(createAccountRequest.getCustomerId())
                .build();
                   return accountDtoConverter.convert(accountRepository.save(account));
    }


    public AccountDto updateAccount(String id, UpdateAccountRequest updateAccountRequest) {
        Customer customer=customerService.getCustomerById(updateAccountRequest.getCustomerId());
        if(customer.getId().equals(" ") || customer.getId()==null){
            return AccountDto.builder().build();
        }
         Optional<Account> accountOptional=accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(updateAccountRequest.getBalance());
            account.setCurreny(updateAccountRequest.getCurreny());
            account.setCustomerId(updateAccountRequest.getCustomerId());
            account.setCity(updateAccountRequest.getCity());
            accountRepository.save(account);
        });
           return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public List<AccountDto> getAllCustomer() {
    List<Account> accountList=accountRepository.findAll();
    return accountList.stream().map(accountDtoConverter::convert).collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id) {
     Optional<Account> accountOptional=accountRepository.findById(id);
     return accountOptional.map(accountDtoConverter::convert)
             .orElse(new AccountDto());
    }
     public void deleteAccount (String id){
         accountRepository.deleteById(id);
     }

}
