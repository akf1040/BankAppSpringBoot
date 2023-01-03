package com.outsource.bankappspringboot.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseCustomerRequest {
    private String name;
    private int dateOfBirth;
    private CityDto city;
    private String adress;
}
