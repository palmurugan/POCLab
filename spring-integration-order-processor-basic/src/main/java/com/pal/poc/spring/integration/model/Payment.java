package com.pal.poc.spring.integration.model;

import com.pal.poc.spring.integration.model.enumerations.PaymentMethod;

import java.time.LocalDate;

public class Payment {

    private PaymentMethod paymentMethod;

    private String cardNumber;

    private LocalDate expirationDate;

    private String cvv;
}
