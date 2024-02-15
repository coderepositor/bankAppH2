package com.sociogen.crudh2db.service;

import com.sociogen.crudh2db.*;
import com.sociogen.crudh2db.exception.ResourceNotFoundException;
import com.sociogen.crudh2db.model.Customer;
import com.sociogen.crudh2db.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}
	@Override
	public Customer updateCustomer(Customer customer) {
	Optional <Customer> customerDb = this.customerRepository.findById(customer.getId());
		if(customerDb.isPresent()) {
			Customer customerUpdate = customerDb.get();
			customerUpdate.setId(customer.getId());
			customerUpdate.setCname(customer.getCname());
			customerUpdate.setLname(customer.getLname());
			customerUpdate.setAcno(customer.getAcno());
			customerRepository.save(customerUpdate);
			return customerUpdate;
		}
		else
		{
			throw new ResourceNotFoundException("Record not found for " + customer.getId());
		}
	}
	@Override
	public List <Customer> getAllCustomer()
	{
		return this.customerRepository.findAll();
	}
	@Override
	public Customer getCustomberById(long customerId) {
		Optional <Customer> customerDb = this.customerRepository.findById(customerId);
		if(customerDb.isPresent()) {
			return customerDb.get();
		}
		else 
		{
			throw new ResourceNotFoundException("Record not found for " + customerId);
		}
	}
	
@Override
	public void deleteCustomer(long id) {
		Optional <Customer> customerDb = this.customerRepository.findById(id);	
		if(customerDb.isPresent()) {
			this.customerRepository.delete(customerDb.get());
		}
		else 
		{
			throw new ResourceNotFoundException("Record not found for " + id);
		}
	}
	
}
