package com.sociogen.crudh2db.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sociogen.crudh2db.model.Customer;
import com.sociogen.crudh2db.service.CustomerService;
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@GetMapping("/customers")
	public ResponseEntity<List <Customer>> getAllCustomers(){
		return ResponseEntity.ok().body(customerService.getAllCustomer());
	}
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable long id){
		return ResponseEntity.ok().body(customerService.getCustomberById(id));
	}
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		return ResponseEntity.ok().body(this.customerService.createCustomer(customer));
	}
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id,@RequestBody Customer customer){
		customer.setId(id);
		return ResponseEntity.ok().body(this.customerService.updateCustomer(customer));
	}
	@DeleteMapping("/customers/{id}")
	public HttpStatus deleteCustomer(@PathVariable long id) {
		this.customerService.deleteCustomer(id);
		return HttpStatus.OK;
	}
}
