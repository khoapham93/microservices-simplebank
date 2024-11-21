package com.kp.accounts.mapper;

import com.kp.accounts.dto.AccountsDto;
import com.kp.accounts.dto.CustomerDto;
import com.kp.accounts.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "accountsDto", target = "accountsDto")
    public CustomerDto toDto(Customer customer, AccountsDto accountsDto);

    public Customer toEntity(CustomerDto customerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customerId", ignore = true)
    Customer updateEntity(CustomerDto customerDto, @MappingTarget Customer customer);
}
