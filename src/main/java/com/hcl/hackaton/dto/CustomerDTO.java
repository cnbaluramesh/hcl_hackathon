package com.hcl.hackaton.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {

	private long customerId;
	private String firstName;
	private String lastName;
	private String dob;
	private String gender;
	private String email;
	private String phone;

}
