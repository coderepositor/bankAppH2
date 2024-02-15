package com.sociogen.crudh2db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sociogen.crudh2db.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{

}
