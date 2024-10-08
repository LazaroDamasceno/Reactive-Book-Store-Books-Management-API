package com.api.v2.customer.utils

import com.api.v2.customer.domain.Customer
import com.api.v2.customer.domain.CustomerRepository
import com.api.v2.customer.exceptions.CustomerNotFoundException
import com.api.v2.exceptions.EmptyEntityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerFinderUtil {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    suspend fun findOne(ssn: String): Customer {
        return withContext(Dispatchers.IO) {
            val customer = customerRepository
                .findAll()
                .filter { e -> e.ssn == ssn && e.archivedAt == null }
                .singleOrNull()
            if (customer == null) throw CustomerNotFoundException()
            customer
        }
    }

    suspend fun findMany(ssn: String): Flow<Customer> {
        return withContext(Dispatchers.IO) {
            val customers = customerRepository.
                findAll()
                .filter { e -> e.ssn == ssn }
            if (customers.count() == 0) throw EmptyEntityException()
            customers
        }
    }

}