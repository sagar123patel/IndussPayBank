package com.IndussPayBank.IndussPayBank.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "User_bank_tbl")
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankId;

	private String bankName;
	private String bankIfsc;

	@Column(unique = true)
	private String accountNumber;

	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

}
