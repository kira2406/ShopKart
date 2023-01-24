package com.ecommerce.Shopkart.Dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message="Product name cannot be empty")
    private String productName;
    @Min(value = 1,message = "product cost cannot be empty")
    private Double productCost;
    @NotBlank(message="Product cannot be empty")
    private String productColor;
    @NotBlank(message="Description cannot be empty")
    private String description;
    @Min(value = 1,message = "numOfProd is not defined !")
    private Integer numOfProd;
}
