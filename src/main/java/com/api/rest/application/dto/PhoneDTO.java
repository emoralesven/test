package com.api.rest.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@Schema(name = "PhoneDTO")
public class PhoneDTO {

    @Id
    private String number;

    private String cityCode;
    private String countryCode;


}
