package com.ecommerce.Shopkart.ServiceImpl;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;
import com.ecommerce.Shopkart.Repo.ProductDetailsRepository;
import com.ecommerce.Shopkart.Service.ShopKartProductService;
import com.ecommerce.Shopkart.Util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShopKartProductServiceImpl implements ShopKartProductService {

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Override
    public GeneralResponse addProduct(ProductInfo productInfo) {
    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+" has invoked");
            if (productInfo != null && Util.stringNullChecker(productInfo.getProductName()) && Util.stringNullChecker(productInfo.getProductColor()) && Util.stringNullChecker(productInfo.getDescription()) && productInfo.getProductCost() > 0.0) {
                productDetailsRepository.save(productInfo);
                log.info("Product Added Successfully");
                return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Product Added Successfully").build();
            } else {
                log.info("Insufficient Data");
                return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Insufficient Data").build();
            }

        }
        catch(Exception e)
        {
            log.error("Exception "+e.getMessage());
            return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        }

    }

    @Override
    public GeneralResponse deleteProduct(ProductInfo productInfo) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try{
            log.info(methodName+" has invoked");
            if(productInfo.getProductId()!=null && productInfo.getProductId()!=0){

                if(productDetailsRepository.existsById(productInfo.getProductId()))
                {
                    productDetailsRepository.deleteById(productInfo.getProductId());
                    log.info("Product deleted successfully");
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Product deleted successfully").build();
                }else {
                    log.info("Product with id "+productInfo.getProductId()+" does not exist");
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.NOT_FOUND.value())).statusDesc("Product with id "+productInfo.getProductId()+" does not exist").build();
                }

            }
            else
            {
                log.info("Insufficient Data");
                return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Insufficient data").build();
            }
        }
        catch(Exception e)
        {
            log.error("Exception "+e.getMessage());
            return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        }
    }

    @Override
    public GeneralResponse getProducts() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        try{
            log.info(methodName+" has invoked");
            List<ProductInfo> products = productDetailsRepository.findAll();
            if(products.size()>0)
            {
                log.info("Products fetched successfully");
                return  GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).products(products).statusDesc("Products fetched successfully").build();
            }
            else{
                return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Products fetch failed").build();

            }

        }
        catch(Exception e)
        {
            log.error("Exception "+e.getMessage());
            return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        }
    }

    @Override
    public GeneralResponse updateProduct(ProductInfo productInfo) {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName + " has invoked");
            if(productInfo != null && productInfo.getProductId()!=null && productInfo.getProductId()!=0 && productDetailsRepository.existsById(productInfo.getProductId())) {

                if (Util.stringNullChecker(productInfo.getProductName()) && Util.stringNullChecker(productInfo.getProductColor()) && Util.stringNullChecker(productInfo.getDescription()) && productInfo.getProductCost()!=null && productInfo.getProductCost() > 0.0) {
                    productDetailsRepository.save(productInfo);
                    log.info("Product updated successfully");
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Product updated successfully").build();
                } else {
                    log.info("Insufficient data");
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Insufficient data").build();
                }
            }
            else {
                if(productInfo!=null && productInfo.getProductId()!=null) {
                    log.info("No product exists with ID " + String.valueOf(productInfo.getProductId()));
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("No product exists with ID " + String.valueOf(productInfo.getProductId())).build();
                }
                else{
                    log.info("No product Id entered");
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("No product Id entered").build();
                }
            }
        } catch (Exception e) {
            log.error("Exception "+e.getMessage());
            return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        }

    }

}
