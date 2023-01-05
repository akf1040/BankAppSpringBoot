package com.outsource.bankappspringboot.dto;


import com.outsource.bankappspringboot.model.City;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CustomerDto {
    private String id;
    private String name;
    private int dateOfBirth;
    private City city;
    private String adress;
}
