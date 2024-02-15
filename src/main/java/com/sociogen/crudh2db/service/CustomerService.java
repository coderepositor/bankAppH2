package com.sociogen.crudh2db.service;
import java.util.List;

import com.sociogen.crudh2db.model.Customer;

public interface CustomerService {
	
	Customer createCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(long id);
	
	List <Customer> getAllCustomer();
	
	Customer getCustomberById(long customerId);
}
