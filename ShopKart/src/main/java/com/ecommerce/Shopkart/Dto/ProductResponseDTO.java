package com.ecommerce.Shopkart.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Integer productId;
    private String productName;
    private Double productCost;
    private String productColor;
    private String description;
    private Integer numOfProd;
}
