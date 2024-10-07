package com.ayesa.reactivemongo.domain;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Represents a Customer entity with various attributes.
 *
 * <p>This class is annotated with Lombok annotations to reduce boilerplate code:
 * <ul>
 *   <li>@Data - Generates getters, setters, toString, equals, and hashCode methods.</li>
 *   <li>@NoArgsConstructor - Generates a no-argument constructor.</li>
 *   <li>@AllArgsConstructor - Generates a constructor with 1 parameter for each field in the class.</li>
 *   <li>@Builder - Produces complex builder APIs for the class.</li>
 * </ul>
 *
 * <p>Attributes:
 * <ul>
 *   <li>id - Unique identifier for the customer.</li>
 *   <li>customerName - Name of the customer.</li>
 *   <li>createdDate - Date and time when the customer record was created.</li>
 *   <li>lastModifiedDate - Date and time when the customer record was last modified.</li>
 * </ul>
 *
 * <p>Created by jt, Spring Framework Guru.
 *
 * @author architecture - rperezv
 * @version 07/10/2024 - 08:10
 * @since jdk 1.17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Customer {

    /**
     * Unique identifier for the customer.
     */
    @Id
    private String id;

    /**
     * Name of the customer.
     */
    @Size(max = 255)
    private String customerName;

    /**
     * Date and time when the customer record was created.
     */
    @CreatedDate
    private LocalDateTime createdDate;

    /**
     * Date and time when the customer record was last modified.
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
