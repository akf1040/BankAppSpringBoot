package com.outsource.bankappspringboot.dto;

import com.outsource.bankappspringboot.model.City;
import com.outsource.bankappspringboot.model.Curreny;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateAccountRequest {
    private String customerId;
    private double balance;
    private Curreny curreny;
    private City city;
}
