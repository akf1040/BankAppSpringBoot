package com.outsource.bankappspringboot.service;

import com.outsource.bankappspringboot.dto.AccountDto;
import com.outsource.bankappspringboot.dto.AccountDtoConverter;
import com.outsource.bankappspringboot.dto.CityDto;
import com.outsource.bankappspringboot.dto.CreateAccountRequest;
import com.outsource.bankappspringboot.model.Account;
import com.outsource.bankappspringboot.model.City;
import com.outsource.bankappspringboot.model.Curreny;
import com.outsource.bankappspringboot.model.Customer;
import com.outsource.bankappspringboot.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private AccountDtoConverter accountDtoConverter;
    private CustomerService customerService;


    @Before
    public void setUp() throws Exception {
        accountRepository = Mockito.mock(AccountRepository.class);
        customerService = Mockito.mock(CustomerService.class);
        accountDtoConverter = Mockito.mock(AccountDtoConverter.class);

        accountService = new AccountService(accountRepository, accountDtoConverter, customerService);
    }

    @Test
    public void whenCreateAccountCallWithValidRequest_itShouldReturnValidAccountDto() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setBalance(100);
        createAccountRequest.setCity(City.İSTANBUL);
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setCurreny(Curreny.TRY);

        Customer customer = Customer.builder()
                .id("12345")
                .name("Akif")
                .city(City.İSTANBUL)
                .adress("Adres")
                .dateOfBirth(1991)
                .build();

        Account account=  Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .city(City.valueOf(createAccountRequest.getCity().name()))
                .curreny(createAccountRequest.getCurreny())
                .customerId(createAccountRequest.getCustomerId())
                .build();

        AccountDto accountDto=AccountDto.builder()
                .id("1234")
                .balance(100)
                .curreny(Curreny.TRY)
                .customerId("12345")
                .build();


        Mockito.when(customerService.getCustomerById("12345")).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenReturn(account);
        Mockito.when(accountDtoConverter.convert(account)).thenReturn(accountDto);

        AccountDto result=accountService.createAccount(createAccountRequest);
        Assert.assertEquals(result,accountDto);
        Mockito.verify(customerService).getCustomerById("12345");
        Mockito.verify(accountRepository).save(account);
        Mockito.verify(accountDtoConverter).convert(account);
    }

    @Test

public void whenCreateAccountCalledWithCustomerWithOutId_itShouldReturnEmptyAccountDto(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setBalance(100);
        createAccountRequest.setCity(City.İSTANBUL);
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setCurreny(Curreny.TRY);

        Mockito.when(customerService.getCustomerById("12345")).thenReturn(Customer.builder().build());

        AccountDto expectedAccountDto= AccountDto.builder().build();
        AccountDto result=accountService.createAccount(createAccountRequest);
        Assert.assertEquals(result,expectedAccountDto);
        Mockito.verifyNoInteractions(accountRepository);
        Mockito.verifyNoInteractions(accountDtoConverter);


    }

}