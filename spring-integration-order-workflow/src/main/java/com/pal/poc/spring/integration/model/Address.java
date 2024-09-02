package com.pal.poc.spring.integration.model;

import lombok.Data;

@Data
public class Address {

    private String streetAddress;

    private String city;

    private String state;

    private String postalCode;

    private String country;

}
