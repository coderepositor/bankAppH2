package com.sociogen.crudh2db;
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

import org.hibernate.annotations.processing.SQL;

import com.sociogen.crudh2db.model.Customer;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAppH2ApplicationTests {
	@Autowired
	private TestH2Repository h2Repository;
	
	@LocalServerPort
	private int port;
	private String baseUrl = "http://localhost";
	public static RestTemplate restTemplate;
	
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	@BeforeEach
	public void setup() {
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1/customers");
	}
	
	@Test
	public void testAddCustomer() {
		Customer customer = new Customer("Prakash","Ambedkar",9922);
		Customer response = restTemplate.postForObject(baseUrl, customer, Customer.class);
		assertEquals("Prakash",response.getCname());
		assertEquals(1,h2Repository.count());
	}
	
	@Test
	@Sql(statements="INSERT INTO CUSTOMERS(Id,cname,lname,acno) VALUES (1,'Parag','Joshi',9001)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements="INSERT INTO CUSTOMERS(Id,cname,lname,acno) VALUES (2,'Prachi','Joshi',9002)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements="DELETE FROM CUSTOMERS WHERE LNAME = 'Joshi'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetCustomers() {
		List<Customer> customers = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(2, customers.size());
	}
	
	
	
}
