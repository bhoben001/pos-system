package com.POS.system.Model;

import com.POS.system.Domain.PaymentType;

import lombok.Data;

@Data
public class PaymentSummary {

    private PaymentType paymentType;
    private double totalAmount;
    private int transactionCount;

    private double percentage;


}
