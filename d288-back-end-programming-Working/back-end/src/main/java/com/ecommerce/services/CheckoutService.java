package com.ecommerce.services;

import com.ecommerce.dto.Purchase;

import javax.validation.Valid;

public interface CheckoutService {
    PurchaseResponse placeOrder(@Valid Purchase purchase);
}
