package com.sanish.loans.repositories;

import com.sanish.loans.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long>{

    Optional<Loans> findByMobileNumber(String mobileNumber);
}