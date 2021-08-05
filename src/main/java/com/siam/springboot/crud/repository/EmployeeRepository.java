package com.siam.springboot.crud.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.siam.springboot.crud.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long>{

	List<Employee> findByEmployeename(String employeeName);

	List<Employee> findByDesignation(String designation);


	
	
}
