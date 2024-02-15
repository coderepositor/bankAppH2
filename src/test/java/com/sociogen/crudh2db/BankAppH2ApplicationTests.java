package com.sociogen.crudh2db;


import org.hibernate.annotations.SQLInserts;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.sociogen.crudh2db.model.Customer;

import jakarta.persistence.SqlResultSetMappings;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAppH2ApplicationTests {

	@Autowired
	private TestH2Repository h2Repository;
	
	@LocalServerPort
	private int port;
	private String baseUrl="http://localhost";
	
	private static RestTemplate restTemplate;
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1/customers");
	}
	
	@Test
	public void testAddCustomer() {;
		Customer customer = new Customer("Anagha", "Joshi",9922);
		Customer response = restTemplate.postForObject(baseUrl, customer, Customer.class);
		assertEquals("Anagha",response.getCname());
		assertEquals(1, h2Repository.count());
	}
	
	@Test
	@Sql(statements= "INSERT INTO CUSTOMERS (id,cname,lname,acno) VALUES (1,'Parag','Joshi',9001)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements= "DELETE FROM CUSTOMERS WHERE CNAME='Parag'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetCustomers() {
	List<Customer>  customers = restTemplate.getForObject(baseUrl, List.class);
	assertEquals(1, customers.size());
	}
			
	
}
