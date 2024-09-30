package com.cesar.trabalho;

import com.cesar.trabalho.models.Customer;
import com.cesar.trabalho.models.FlightTicket;
import com.cesar.trabalho.models.shared.Id;
import com.cesar.trabalho.repositories.CustomerRepository;
import com.cesar.trabalho.repositories.FlightTicketRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public Customer save(Customer customer) {
        this.customers.put(customer.getId().toString(), customer);
        return customer;
    }
}
