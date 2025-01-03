package com.hcl.hackaton.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Data
public class CustomerDTO {
	
	private long customerId;
	private String firstName;
	private String lastName;
	private String dob;
	private String gender;
	private String email;
	private String phone;

}
