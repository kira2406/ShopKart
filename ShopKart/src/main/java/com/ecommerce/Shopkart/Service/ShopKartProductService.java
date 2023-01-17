package com.ecommerce.Shopkart.Service;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;

public interface ShopKartProductService {
    GeneralResponse addProduct(ProductInfo productInfo);

    GeneralResponse deleteProduct(ProductInfo productInfo);

    GeneralResponse getProducts();

    GeneralResponse updateProduct(ProductInfo productInfo);
}
