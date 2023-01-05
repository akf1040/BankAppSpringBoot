package com.outsource.bankappspringboot.service;

import com.outsource.bankappspringboot.dto.CityDto;
import com.outsource.bankappspringboot.dto.CreateCustomerRequest;
import com.outsource.bankappspringboot.dto.CustomerDto;
import com.outsource.bankappspringboot.dto.CustomerDtoConverter;
import com.outsource.bankappspringboot.model.City;
import com.outsource.bankappspringboot.model.Customer;
import com.outsource.bankappspringboot.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class CustomerServiceTest {

    private CustomerService customerService;
    private  CustomerRepository customerRepository;
    private   CustomerDtoConverter customerDtoConverter;

    @Before
    public void setUp() throws Exception {
        customerService= Mockito.mock(CustomerService.class);
        customerRepository=Mockito.mock(CustomerRepository.class);
        customerDtoConverter=Mockito.mock(CustomerDtoConverter.class);
        customerService= new CustomerService( customerRepository,  customerDtoConverter);
    }

    @Test
    public void createCustomer() {
        CreateCustomerRequest createCustomerRequest= new CreateCustomerRequest("12345");
        createCustomerRequest.setCity(CityDto.İSTANBUL);
        createCustomerRequest.setName("Akif");
        createCustomerRequest.setAdress("Adres");
        createCustomerRequest.setDateOfBirth(1991);

        Customer customer = Customer.builder()
                .id("12345")
                .name("Akif")
                .city(City.İSTANBUL)
                .adress("Adres")
                .dateOfBirth(1991)
                .build();

        CustomerDto customerDto=CustomerDto.builder()
                .adress("Adres")
                .name("Akif")
                .city(City.İSTANBUL)
                .dateOfBirth(1991)
                .id("12345")
                .build();

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);

        CustomerDto results=customerService.createCustomer(createCustomerRequest);
        Assert.assertEquals(results,customerDto);
        Mockito.verify(customerRepository).save(customer);
        Mockito.verify(customerDtoConverter).convert(customer);
    }
}