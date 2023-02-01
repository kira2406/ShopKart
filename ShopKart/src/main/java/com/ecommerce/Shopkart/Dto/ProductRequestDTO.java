package com.ecommerce.Shopkart.Dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message="Product name cannot be empty")
    private String productName;
    @Min(value = 1,message = "product cost cannot be empty")
    @NotNull(message="product cost cannot be null")
    private Double productCost;
    @NotBlank(message="Product cannot be empty")
    private String productColor;
    @NotBlank(message="Description cannot be empty")
    private String description;
    @Min(value = 1,message = "numOfProd is not defined !")
    @NotNull(message="numOfProd cannot be null")
    private Integer numOfProd;
}
