package com.hcl.hackaton.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
	
	private long customerId;
	private String firstName;
	private String lastName;
	private String dob;
	private String gender;
	private String email;
	private String phone;

}
