package com.ecommerce.Shopkart.Service;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductRequestDTO;
import com.ecommerce.Shopkart.Dto.ProductResponseDTO;

import java.util.List;

public interface ShopKartProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    GeneralResponse<?> deleteProduct(Integer productId);

    List<ProductResponseDTO> getProducts();

    GeneralResponse<?> updateProduct(Integer productId, ProductRequestDTO productRequestDTO);

    ProductResponseDTO getProductById(Integer productId);
}
