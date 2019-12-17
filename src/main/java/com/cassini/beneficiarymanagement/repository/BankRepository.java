package com.cassini.beneficiarymanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cassini.beneficiarymanagement.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

}
