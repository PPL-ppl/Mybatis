package org.example.Resposty;

import org.example.Entiy.Customer;

public interface CustomerRepository {
    Customer findById(long id);
}
