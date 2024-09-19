package com.IndussPayBank.IndussPayBank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	@NotBlank(message = "First name is required.")
	private String firstName;

	@NotBlank(message = "Last name is required.")
	private String lastName;

	@Email(message = "Email should be valid.")
	@NotBlank(message = "Email is required.")
	private String email;

	@NotBlank(message = "Phone is required.")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits.")
	private String phone;

	private BankDto bank;
}
