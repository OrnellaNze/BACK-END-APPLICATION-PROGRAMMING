package com.ecommerce.bootstrap;

import com.ecommerce.dao.CountryRepository;
import com.ecommerce.dao.CustomerRepository;
import com.ecommerce.dao.DivisionRepository;
import com.ecommerce.entities.Country;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

    @Component
    public class BootStrapData implements CommandLineRunner {

        private final CustomerRepository customerRepository;
        private final DivisionRepository divisionRepository;
        private final CountryRepository countryRepository;

        @Autowired
        public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository, CountryRepository countryRepository) {
            this.customerRepository = customerRepository;
            this.divisionRepository = divisionRepository;
            this.countryRepository = countryRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            // Create a new Country object and persist it
            Country country = new Country();
            country.setCountry_name("Country Name");
            countryRepository.save(country);

            // Create and set Division properties
            Division division = new Division();
            division.setDivisionName("Division Name");
            division.setCreateDate(new Date());
            division.setLastUpdate(new Date());

            // Set the persisted country to the division
            division.setCountry(country);

            // Save the division
            divisionRepository.save(division);

            // Generate sample customers with the created division
            List<Customer> sampleCustomers = generateSampleCustomers(division);

            // Save sample customers to the database
            customerRepository.saveAll(sampleCustomers);
        }

        private List<Customer> generateSampleCustomers(Division division) {
            List<Customer> sampleCustomers = new ArrayList<>();
            sampleCustomers.add(new Customer(1L, "Jane", "Joseph", "123 High St", "12345", "541-1234", new Date(), new Date(), division, new HashSet<>()));
            sampleCustomers.add(new Customer(2L, "Jane", "Smith", "456 Elm St", "67890", "555-5678", new Date(), new Date(), division, new HashSet<>()));
            sampleCustomers.add(new Customer(3L, "Michael", "Johnson", "789 Oak St", "54321", "555-9876", new Date(), new Date(), division, new HashSet<>()));
            sampleCustomers.add(new Customer(4L, "Emily", "Brown", "321 Maple St", "09876", "555-4321", new Date(), new Date(), division, new HashSet<>()));
            sampleCustomers.add(new Customer(5L, "David", "Wilson", "654 Pine St", "13579", "555-2468", new Date(), new Date(), division, new HashSet<>()));
            return sampleCustomers;
        }
    }
