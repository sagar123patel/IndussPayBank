package com.IndussPayBank.IndussPayBank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDto {

	@NotBlank(message = "Bank name is required.")
	private String bankName;

	@NotBlank(message = "Bank IFSC is required.")
	private String bankIfsc;

	@NotBlank(message = "Account number is required.")
	private String accountNumber;
}