package com.siam.springboot.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siam.springboot.crud.exception.ResourceNotFoundException;
import com.siam.springboot.crud.model.Employee;
import com.siam.springboot.crud.repository.EmployeeRepository;
import com.siam.springboot.crud.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")

@Slf4j
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		log.info("Fetching all employee record");
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee unavailable for id: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	@GetMapping("/employees/empname/{employeename}")
	public List<Employee> getEmployeeByName(@PathVariable(value = "employeename") String employeename)
			throws ResourceNotFoundException {
		log.info("Fetch employee record as per name");
		return employeeRepository.findByEmployeename(employeename);
	}
	
	
	@GetMapping("/employees/designation/{designation}")
	public List<Employee> getEmployeeByDesignation(@PathVariable(value = "designation") String designation)
			throws ResourceNotFoundException {
		log.info("Fetch employee record as per designation");
		return employeeRepository.findByDesignation(designation);
	}
	

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		log.info("Persisting employee record");
		return employeeRepository.save(employee);
		
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee unavailable id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setEmployeename(employeeDetails.getEmployeename());
		employee.setDesignation(employeeDetails.getDesignation());
		final Employee updatedEmployee = employeeRepository.save(employee);
		log.info("successfuly updated employee record");
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee unavailable id :: " + employeeId));

		employeeRepository.delete(employee);
		log.info("successfuly deleted employee id");
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
