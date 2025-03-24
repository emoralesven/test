package com.api.rest.domain.model;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    private String number;

    private String cityCode;
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBank user;
}