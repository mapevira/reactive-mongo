package com.ayesa.reactivemongo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a Beer Data Transfer Object (DTO) with various attributes.
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
 * @version 07/10/2024 - 08:17
 * @since jdk 1.17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {

    /**
     * Unique identifier for the beer.
     */
    private String id;

    /**
     * Name of the beer.
     * Must not be blank and must be between 3 and 255 characters.
     */
    @NotBlank
    @Size(min = 3, max = 255)
    private String beerName;

    /**
     * Style of the beer (e.g., Lager, Ale).
     * Must be between 1 and 255 characters.
     */
    @Size(min = 1, max = 255)
    private String beerStyle;

    /**
     * Universal Product Code for the beer.
     * Maximum length is 25 characters.
     */
    @Size(max = 25)
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
