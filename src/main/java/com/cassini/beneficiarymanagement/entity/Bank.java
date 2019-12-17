package com.cassini.beneficiarymanagement.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Bank {
	
	@Id
	private Integer bankId;
	private String bankName;
	private String ifscCode;
	private String branchName;
	private String address;

}
