package com.outsource.bankappspringboot.dto;

import com.outsource.bankappspringboot.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {
    public AccountDto convert(Account account){
        AccountDto accountDto=AccountDto.builder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .curreny(account.getCurreny())
                .balance(account.getBalance())
                .city(CityDto.valueOf(account.getCity().name()))
                .build();
        return accountDto;
    }

}
