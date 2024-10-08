package com.api.v1.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.v1.annotations.SSN;
import com.api.v1.domain.repositories.CustomerRepository;
import com.api.v1.dtos.responses.CustomerResponseDto;
import com.api.v1.mappers.customer.CustomerResponseMapper;
import com.api.v1.utils.customer.CustomerFinderUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class CustomersRetrieveServiceImpl implements CustomersRetrieveService {

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Autowired 
    private CustomerRepository repository;

    @Override
    public Flux<CustomerResponseDto> retrieveAll() {
        return repository
            .findAll()
            .flatMap(customer -> Flux.just(CustomerResponseMapper.map(customer)));
    }

    @Override
    public Mono<CustomerResponseDto> retrieveBySsn(@SSN String ssn) {
        return customerFinderUtil
            .find(ssn)
            .flatMap(customer -> Mono.just(CustomerResponseMapper.map(customer)));
    }
    
}
