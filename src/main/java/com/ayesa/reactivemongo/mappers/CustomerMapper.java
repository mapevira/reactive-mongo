package com.ayesa.reactivemongo.mappers;

import com.ayesa.reactivemongo.domain.Customer;
import com.ayesa.reactivemongo.model.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between Customer and CustomerDTO objects.
 *
 * <p>This interface uses MapStruct to generate the implementation code for mapping
 * between Customer and CustomerDTO objects.
 *
 * <p>Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 07/10/2024 - 08:33
 * @since jdk 1.21
 */
@Mapper
public interface CustomerMapper {

    /**
     * Converts a Customer object to a CustomerDTO object.
     *
     * @param customer - Customer object to convert.
     * @return CustomerDTO object.
     */
    CustomerDTO customerToCustomerDTO(Customer customer);

    /**
     * Converts a CustomerDTO object to a Customer object.
     *
     * @param customerDTO - CustomerDTO object to convert.
     * @return Customer object.
     */
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
