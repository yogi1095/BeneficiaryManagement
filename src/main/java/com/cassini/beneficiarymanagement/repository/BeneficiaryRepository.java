package com.cassini.beneficiarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cassini.beneficiarymanagement.entity.Account;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.google.common.base.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
	
	long countByCustomer(Customer customer);
	Optional<Beneficiary> findByBeneficiaryAccountAndCustomer(Account account, Customer customer);

}
