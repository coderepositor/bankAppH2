package com.sociogen.crudh2db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sociogen.crudh2db.model.Customer;

public interface TestH2Repository extends JpaRepository<Customer, Long>{

}
