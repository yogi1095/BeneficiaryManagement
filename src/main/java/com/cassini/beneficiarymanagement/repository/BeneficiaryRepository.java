package com.cassini.beneficiarymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cassini.beneficiarymanagement.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

	public Optional<Beneficiary> findByBeneficiaryId(Integer beneficiaryId);

	


}
