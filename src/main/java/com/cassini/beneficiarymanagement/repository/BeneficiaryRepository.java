package com.cassini.beneficiarymanagement.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
	
	List<Beneficiary> findAllByCustomerOrderByBeneficiaryNameAsc(Customer customerId);

	public Optional<Beneficiary> findByBeneficiaryId(Integer beneficiaryId);

	


}
