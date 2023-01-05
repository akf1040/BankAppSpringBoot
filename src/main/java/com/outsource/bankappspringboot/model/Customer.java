package com.outsource.bankappspringboot.model;


import com.outsource.bankappspringboot.dto.CityDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Customer {
    @Id
    private String id;

    private String name;
    private int dateOfBirth;
    @Enumerated
    private City city;
    private String adress;
}
