package com.ecommerce.services;

import com.ecommerce.dao.CartRepository;
import com.ecommerce.dao.CustomerRepository;
import com.ecommerce.dto.Purchase;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static com.ecommerce.entities.StatusType.ordered;

@Service
public class CheckoutServiceImpl implements CheckoutService{


        private final CustomerRepository customerRepository;
        private final CartRepository cartRepository;

        public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
            this.customerRepository = customerRepository;
            this.cartRepository = cartRepository;
        }

        @Override
        @Transactional
        public PurchaseResponse placeOrder(Purchase purchase) {
            Cart cart = purchase.getCart();
            cart.setStatusType(ordered);
            // generate Tracking Number
            String orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);

            // populate with cartItems
            Set<CartItem> cartItems = purchase.getCartItems();
            cartItems.forEach(item -> {
                cart.add(item);
                item.setCart(cart);
                item.getExcursions().forEach(excursion -> {
                    excursion.setVacation(item.getVacation());
                    excursion.getCartItems().add(item);
                });
            });
            cartRepository.save(cart);

            //populate customer with cart
            //Customer customer = purchase.getCustomer();
           // customer.add(cart);

            // save to database
           // customerRepository.save(customer);

            // return response with tracking number
            return new PurchaseResponse(orderTrackingNumber);
        }

        // Method to generate order tracking number
        private String generateOrderTrackingNumber() {
            // Generate a random UUID number (UUID-4)
            return UUID.randomUUID().toString();
        }
    }

