package com.kp.accounts.mapper;

import com.kp.accounts.dto.AccountsDto;
import com.kp.accounts.entity.Accounts;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

    AccountsDto toDto(Accounts accounts);

    Accounts toEntity(AccountsDto accountsDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    Accounts updateEntity(AccountsDto accountsDto, @MappingTarget Accounts accounts);
}
