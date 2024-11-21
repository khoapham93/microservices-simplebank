package com.kp.loans.mapper;

import com.kp.loans.dto.LoansDto;
import com.kp.loans.entity.Loans;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-20T13:36:12+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.3.1 (BellSoft)"
)
@Component
public class LoansMapperImpl implements LoansMapper {

    @Override
    public LoansDto toDto(Loans loans) {
        if ( loans == null ) {
            return null;
        }

        LoansDto loansDto = new LoansDto();

        loansDto.setMobileNumber( loans.getMobileNumber() );
        loansDto.setLoanNumber( loans.getLoanNumber() );
        loansDto.setLoanType( loans.getLoanType() );
        loansDto.setTotalLoan( loans.getTotalLoan() );
        loansDto.setAmountPaid( loans.getAmountPaid() );
        loansDto.setOutstandingAmount( loans.getOutstandingAmount() );

        return loansDto;
    }

    @Override
    public Loans toEntity(LoansDto loansDto) {
        if ( loansDto == null ) {
            return null;
        }

        Loans loans = new Loans();

        loans.setMobileNumber( loansDto.getMobileNumber() );
        loans.setLoanNumber( loansDto.getLoanNumber() );
        loans.setLoanType( loansDto.getLoanType() );
        loans.setTotalLoan( loansDto.getTotalLoan() );
        loans.setAmountPaid( loansDto.getAmountPaid() );
        loans.setOutstandingAmount( loansDto.getOutstandingAmount() );

        return loans;
    }

    @Override
    public Loans updateEntity(LoansDto loansDto, Loans loans) {
        if ( loansDto == null ) {
            return loans;
        }

        if ( loansDto.getMobileNumber() != null ) {
            loans.setMobileNumber( loansDto.getMobileNumber() );
        }
        if ( loansDto.getLoanNumber() != null ) {
            loans.setLoanNumber( loansDto.getLoanNumber() );
        }
        if ( loansDto.getLoanType() != null ) {
            loans.setLoanType( loansDto.getLoanType() );
        }
        loans.setTotalLoan( loansDto.getTotalLoan() );
        loans.setAmountPaid( loansDto.getAmountPaid() );
        loans.setOutstandingAmount( loansDto.getOutstandingAmount() );

        return loans;
    }
}
