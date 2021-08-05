package com.siam.springboot.crud.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection = "Employee")
public class Employee {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id

	private long id;
	
	@Size(max=40) 
    @Indexed
	private String designation;
	
	@NotBlank
    @Size(max=50)
    @Indexed 
	private String employeename;
	
	@NotBlank
    @Size(max=100)

	private String emailId;

}
