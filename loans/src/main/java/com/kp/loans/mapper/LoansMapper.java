package com.kp.loans.mapper;

import com.kp.loans.dto.LoansDto;
import com.kp.loans.entity.Loans;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LoansMapper {
    LoansDto toDto(Loans loans);

    Loans toEntity(LoansDto loansDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "loanId", ignore = true)
    Loans updateEntity(LoansDto loansDto, @MappingTarget Loans loans);
}
