package com.sociogen.crudh2db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sociogen.crudh2db.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	List<Customer> findByAcno(double acno);
	Optional<Customer> findByCname(String cname);
} 
