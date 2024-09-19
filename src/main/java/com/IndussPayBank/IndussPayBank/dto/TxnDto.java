package com.IndussPayBank.IndussPayBank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TxnDto {
	
	@NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Service type is required")
    private String service;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be non-negative")
    private Double amount;

    private Double gst;

    private Double commission;
}