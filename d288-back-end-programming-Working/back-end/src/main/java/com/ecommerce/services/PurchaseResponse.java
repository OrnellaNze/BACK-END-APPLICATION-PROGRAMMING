package com.ecommerce.services;

import lombok.Data;

@Data


public class PurchaseResponse {
    private String orderTrackingNumber;

    public PurchaseResponse(String orderTrackingNumber) {

        this.orderTrackingNumber = orderTrackingNumber;
    }

}

