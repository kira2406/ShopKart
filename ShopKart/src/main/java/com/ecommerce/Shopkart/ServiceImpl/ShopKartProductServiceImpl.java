package com.ecommerce.Shopkart.ServiceImpl;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Entity.ProductInfo;
import com.ecommerce.Shopkart.Dto.ProductRequestDTO;
import com.ecommerce.Shopkart.Dto.ProductResponseDTO;
import com.ecommerce.Shopkart.Exception.ProductNotFoundException;
import com.ecommerce.Shopkart.Exception.ProductServiceBusinessException;
import com.ecommerce.Shopkart.Repo.ProductDetailsRepository;
import com.ecommerce.Shopkart.Service.ShopKartProductService;
import com.ecommerce.Shopkart.Util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopKartProductServiceImpl implements ShopKartProductService {

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO;
        try {
            log.info("addProduct() has invoked");
                ProductInfo product = Util.convertToEntity(productRequestDTO);
                ProductInfo createdProduct = productDetailsRepository.save(product);
                log.info("Product Added Successfully");
                productResponseDTO = Util.convertToDTO(createdProduct);
        }
        catch(Exception ex)
        {
            log.error("Exception occurred while storing product to database , Exception message {}", ex.getMessage());
            throw new ProductServiceBusinessException("Exception occurred while create a new product");
        }
        return productResponseDTO;

    }

    @Override
    public GeneralResponse<?> deleteProduct(Integer productId) {

        try{
                if(productDetailsRepository.existsById(productId))
                {
                    productDetailsRepository.deleteById(productId);
                    log.info("Product deleted successfully");
                } else {
                    log.info("Product with id "+productId+" does not exist");
                    throw new ProductNotFoundException("No product exists with product ID "+productId);
                }
        }
        catch(ProductNotFoundException ex){
            log.error("Exception occurred: {}",ex.getMessage());
            throw new ProductNotFoundException(ex.getMessage());
        }
        catch(Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            throw new ProductServiceBusinessException("Exception occurred while deleting product");
        }
        return GeneralResponse.builder().status("SUCCESS").build();
    }

    @Override
    public List<ProductResponseDTO> getProducts() {

        List<ProductResponseDTO> productResponseDTOS = null;
        
        try{
            log.info("getProducts() has invoked");
            List<ProductInfo> products = productDetailsRepository.findAll();
            if(products.size()>0)
            {
                log.info("Products fetched successfully");
                productResponseDTOS = products.stream()
                        .map(Util::convertToDTO)
                        .collect(Collectors.toList());
            }
            else{
                productResponseDTOS = Collections.emptyList();
            }

        }
        catch(Exception ex)
        {
            log.error("Exception occurred: {}",ex.getMessage());
            throw new ProductServiceBusinessException("Exception occurred when fetching products from Database");
        }
        return productResponseDTOS;
    }

    @Override
    public GeneralResponse<?> updateProduct(Integer productId, ProductRequestDTO productRequestDTO) {
        try {
            log.info("updateProduct() method has invoked");
            if(productDetailsRepository.existsById(productId)){
                ProductInfo productInfo = Util.convertToEntity(productRequestDTO);
                productInfo.setProductId(productId);
                productDetailsRepository.save(productInfo);
            }else{
                throw new ProductNotFoundException("No product exists with product ID "+productId);
            }

        }
        catch(ProductNotFoundException ex){
            log.error("Exception occurred: {}",ex.getMessage());
            throw new ProductNotFoundException(ex.getMessage());
        }
        catch (Exception ex) {
            log.error("Exception occurred: {}",ex.getMessage());
            throw new ProductServiceBusinessException("Exception occurred when updating product");
        }

        return GeneralResponse.builder().status("SUCCESS").build();
    }

    @Override
    public ProductResponseDTO getProductById(Integer productId) {
        ProductResponseDTO productResponseDTO;

        //String supplierCode="";
        try {
            log.info("getProductById() has invoked");

            ProductInfo product = productDetailsRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + productId));
            productResponseDTO = Util.convertToDTO(product);

        }
        catch(ProductNotFoundException ex){
            log.error("Exception occurred while fetching product {} from database , Exception message {}", productId, ex.getMessage());
            throw new ProductNotFoundException(ex.getMessage());
        }
        catch (Exception ex) {
            log.error("Exception occurred while fetching product {} from database , Exception message {}", productId, ex.getMessage());
            throw new ProductServiceBusinessException("Exception occurred while fetch product from Database with id " + productId);
        }

        return productResponseDTO;
    }

}
