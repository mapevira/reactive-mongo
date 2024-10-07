package com.ayesa.reactivemongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Represents a Beer entity with various attributes.
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
 *   <li>id - Unique identifier for the beer.</li>
 *   <li>beerName - Name of the beer.</li>
 *   <li>beerStyle - Style of the beer (e.g., Lager, Ale).</li>
 *   <li>upc - Universal Product Code for the beer.</li>
 *   <li>quantityOnHand - Quantity of beer available in stock.</li>
 *   <li>price - Price of the beer.</li>
 *   <li>createdDate - Date and time when the beer record was created.</li>
 *   <li>lastModifiedDate - Date and time when the beer record was last modified.</li>
 * </ul>
 *
 * <p>Created by jt, Spring Framework Guru.
 *
 * @author architecture - rperezv
 * @version 07/10/2024 - 08:04
 * @since jdk 1.17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Beer {

    /**
     * Unique identifier for the beer.
     */
    @Id
    private String id;

    /**
     * Name of the beer.
     */
    private String beerName;

    /**
     * Style of the beer (e.g., Lager, Ale).
     */
    private String beerStyle;

    /**
     * Universal Product Code for the beer.
     */
    private String upc;

    /**
     * Quantity of beer available in stock.
     */
    private Integer quantityOnHand;

    /**
     * Price of the beer.
     */
    private Double price;

    /**
     * Date and time when the beer record was created.
     */
    private LocalDateTime createdDate;

    /**
     * Date and time when the beer record was last modified.
     */
    private LocalDateTime lastModifiedDate;

}
