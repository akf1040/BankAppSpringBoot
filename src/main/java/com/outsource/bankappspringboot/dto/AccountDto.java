package com.outsource.bankappspringboot.dto;

import com.outsource.bankappspringboot.model.City;
import com.outsource.bankappspringboot.model.Curreny;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AccountDto {
    private String id;

    private String customerId;
    private double balance;
    private CityDto city;
    private Curreny curreny;
}
