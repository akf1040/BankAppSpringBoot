package com.outsource.bankappspringboot.dto;

import com.outsource.bankappspringboot.model.City;
import com.outsource.bankappspringboot.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public CustomerDto convert(Customer customer) {
        CustomerDto customerDto=new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setCity(City.valueOf(customer.getCity().name()));
        customerDto.setAdress(customer.getAdress());
        customerDto.setDateOfBirth(customer.getDateOfBirth());

        return customerDto;

    }

}
