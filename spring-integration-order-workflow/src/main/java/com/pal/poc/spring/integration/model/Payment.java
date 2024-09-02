package com.pal.poc.spring.integration.model;

import com.pal.poc.spring.integration.model.enumerations.PaymentMethod;
import lombok.Data;

@Data
public class Payment {

    private PaymentMethod paymentMethod;

    private String cardNumber;

    private String expirationDate;

    private String cvv;
}
