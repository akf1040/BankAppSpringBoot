package com.outsource.bankappspringboot.service;


import com.outsource.bankappspringboot.dto.*;
import com.outsource.bankappspringboot.model.City;
import com.outsource.bankappspringboot.model.Customer;
import com.outsource.bankappspringboot.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final  CustomerRepository customerRepository;
    private  final CustomerDtoConverter customerDtoConverter;
    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }


    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
       Customer customer= new Customer();
       customer.setId(createCustomerRequest.getId());
       customer.setName(createCustomerRequest.getName());
       customer.setAdress(createCustomerRequest.getAdress());
       customer.setCity(City.valueOf(createCustomerRequest.getCity().name()));
       customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());


       return  customerDtoConverter.convert(customerRepository.save(customer));
    }

    public List<CustomerDto> getAllCustomer() {
        List<Customer> customerList=customerRepository.findAll();
        return customerList.stream().map(customerDtoConverter::convert).collect(Collectors.toList());
    }


    public CustomerDto getCustomerDtoById(String id) {
        return customerRepository.findById(id).map(customerDtoConverter::convert).orElse(new CustomerDto());
    }

    public CustomerDto updateCustomer(String id, UpdateCustomerRequest updateCustomerRequest) {
        Optional<Customer> customerOptional=customerRepository.findById(id);
        customerOptional.ifPresent(customer -> {
            customer.setName(updateCustomerRequest.getName());
            customer.setCity(City.valueOf(updateCustomerRequest.getCity().name()));
            customer.setAdress(updateCustomerRequest.getAdress());
            customer.setDateOfBirth(updateCustomerRequest.getDateOfBirth());
            customerRepository.save(customer);

                });
        return customerOptional.map(customerDtoConverter::convert).orElse(new CustomerDto());
    }
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);

    }
    protected Customer getCustomerById(String id){
        return customerRepository.findById(id).orElse(new Customer());
    }
}

