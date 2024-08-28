package com.api.v1.mappers;

import com.api.v1.domain.entitties.Customer;
import com.api.v1.dtos.responses.CustomerResponseDto;

public class CustomerResponseMapper {

    public static CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
            customer.getFullName(), 
            customer.getSsn(), 
            customer.getBirthDate(), 
            customer.getEmail(), 
            customer.getAddress(), 
            customer.getPhoneNumber(), 
            customer.getGender()
        );
    }
    
}
