package com.sociogen.crudh2db.service;

import com.sociogen.crudh2db.*;
import com.sociogen.crudh2db.exception.ResourceNotFoundException;
import com.sociogen.crudh2db.model.Customer;
import com.sociogen.crudh2db.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}

	@CachePut("CustomerCache")
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
		

	public List<Customer> applyTogglz(List<Customer> availableCustomers) {
        List<Customer> luckyDrawCustomers = new ArrayList<>();
        // Iterating using for loop
        for (int i = 0; i < availableCustomers.size(); i++) 
            
        {
        	Customer objCustomer = availableCustomers.get(i);
        	objCustomer.setLname(objCustomer.getCname()+" Lucky Draw Participant " + i);
	luckyDrawCustomers.add(objCustomer);	
        }
  
        return luckyDrawCustomers;
    }

	
	/*@Cacheable("CustomerCache", unless ="#result" == null)*/
	@Cacheable(value="CustomerCache")
	public Customer getCustomberById(long customerId) {
		System.out.println("customerid" + customerId);
		logger.info("Fetch the customer data for " + customerId);
		Optional <Customer> customerDb = this.customerRepository.findById(customerId);
		if(customerDb.isPresent()) {
			return customerDb.get();
		}
		else 
		{
			throw new ResourceNotFoundException("Record not found for " + customerId);
		}
	}
	
	
	@CacheEvict("CustomerCache")
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
	
	
	@Cacheable(value="CustomerCache",condition="#cname.length>5")
	@Override
	public Customer getCustomerByName(String cname) {
		System.out.println("first name" + cname);
		logger.info("Fetch the customer data for " + cname);
		Optional <Customer> customerDb = this.customerRepository.findByCname(cname);
		if(customerDb.isPresent()) {
			return customerDb.get();
		}
		else 
		{
			throw new ResourceNotFoundException("Record not found for " + cname);
		}

	}
	
}
