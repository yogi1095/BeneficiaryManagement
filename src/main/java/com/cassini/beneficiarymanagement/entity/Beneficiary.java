package com.cassini.beneficiarymanagement.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Beneficiary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer beneficiaryId;
	private String beneficiaryName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "from_account_number")
	private Account fromAccount;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "to_account_number")
	private Account toAccount;

}
