package com.IndussPayBank.IndussPayBank.modal;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "User_txn_tbl")
public class Txn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String txnId; // Make sure this is a String

	private String customerName;

	private String service;

	@NotNull
	@Min(0)
	private Double amount;

	private Double gst;

	private Double commission;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;

}
