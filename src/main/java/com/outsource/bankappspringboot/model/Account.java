package com.outsource.bankappspringboot.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Account {
    @Id
    private String id;

    private String customerId;
    private double balance;
    private City city;
    private Curreny curreny;
}
