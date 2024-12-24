package com.sanish.loans.services.impl;

import com.sanish.loans.constants.LoansConstants;
import com.sanish.loans.dto.LoansDto;
import com.sanish.loans.entities.Loans;
import com.sanish.loans.exceptions.LoanAlreadyExistsException;
import com.sanish.loans.exceptions.ResourceNotFoundException;
import com.sanish.loans.mappers.LoansMapper;
import com.sanish.loans.repositories.LoansRepository;
import com.sanish.loans.services.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber - Mobile number of customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans =  loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists for customer with mobile number : " + mobileNumber);
        }

        Loans newLoanDetails = initiateNewLoan(mobileNumber);
        loansRepository.save(newLoanDetails);
    }


    private Loans initiateNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        //newLoan.setCreatedAt(LocalDateTime.now()); //Audit fields
        //newLoan.setCreatedBy("Anonymous"); //Audit fields
        return newLoan;
    }

    /**
     * @param mobileNumber - Mobile number of customer
     * @return Loan details of the customer
     */
    @Override
    public LoansDto getLoan(String mobileNumber) {
        Loans fetchedLoanDetails = loansRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));

        LoansDto loansDto = LoansMapper.mapToLoansDto(fetchedLoanDetails,new LoansDto());
        return loansDto;
    }

    /**
     * @param loansDto - LoansDto Object
     * @return Boolean value indicating if the loan details were updated
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans fetchedLoanDetails = loansRepository
                .findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber()));

        LoansMapper.mapToLoans(loansDto,fetchedLoanDetails); //Save new loan details to fetched Loans object
        loansRepository.save(fetchedLoanDetails);
        return true;
    }

    /**
     * @param mobileNumber - Mobile number of the customer
     * @return Boolean value indicating if loan details corresponding to mobile number were deleted
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans fetchedLoanDetails = loansRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        loansRepository.deleteById(fetchedLoanDetails.getLoanId());
        return true;
    }

}
