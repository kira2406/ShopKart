package com.ecommerce.Shopkart.Util;

import com.ecommerce.Shopkart.Entity.ProductInfo;
import com.ecommerce.Shopkart.Dto.ProductRequestDTO;
import com.ecommerce.Shopkart.Dto.ProductResponseDTO;

public class Util {

    public static ProductInfo convertToEntity(ProductRequestDTO productRequestDTO) {
        ProductInfo product = new ProductInfo();
        product.setProductName(productRequestDTO.getProductName());
        product.setDescription(productRequestDTO.getDescription());
        product.setProductCost(productRequestDTO.getProductCost());
        product.setProductColor(productRequestDTO.getProductColor());
        product.setNumOfProd(productRequestDTO.getNumOfProd());
        return product;
    }
    public static ProductResponseDTO convertToDTO(ProductInfo product){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(product.getProductId());
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setProductColor(product.getProductColor());
        productResponseDTO.setNumOfProd(product.getNumOfProd());
        productResponseDTO.setProductCost(product.getProductCost());
        return productResponseDTO;
    }
    public static boolean stringNullChecker(String str){
        return str!=null && !str.isEmpty() && !str.equalsIgnoreCase("null") && !str.equalsIgnoreCase("na");

    }
}
