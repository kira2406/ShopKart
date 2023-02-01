package com.ecommerce.Shopkart;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Entity.ProductInfo;
import com.ecommerce.Shopkart.Repo.ProductDetailsRepository;
import com.ecommerce.Shopkart.ServiceImpl.ShopKartProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopKartProductServiceImplTests {

    @InjectMocks
    ShopKartProductServiceImpl shopKartProductService;

    @Mock
    ProductDetailsRepository productDetailsRepository;

    ProductInfo productInfo = new ProductInfo();

    @Test
    public void addProductNullTest()
    {
        GeneralResponse response = shopKartProductService.addProduct(null);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Insufficient Data").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());

    }

    @Test
    public void addProductTest()
    {
        ProductInfo productInfo1 = new ProductInfo();
        productInfo1.setProductName("Shampoo");
        productInfo1.setProductCost(90.2);
        productInfo1.setProductColor("white");
        productInfo1.setDescription("Shampoo for hair");
        productInfo1.setNumOfProd(70);

        GeneralResponse response = shopKartProductService.addProduct(productInfo1);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Product Added Successfully").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());

    }

    @Test
    public void addProductExceptionTest()
    {
        when(productDetailsRepository.save(any())).thenThrow(new RuntimeException());

        productInfo.setProductName("Shampoo");
        productInfo.setProductCost(90.2);
        productInfo.setProductColor("white");
        productInfo.setDescription("Shampoo for hair");
        productInfo.setNumOfProd(70);

        GeneralResponse response = shopKartProductService.addProduct(productInfo);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());

    }

    @Test
    public void deleteProductProductExistsTest()
    {
        productInfo.setProductId(3);

        when(productDetailsRepository.existsById(productInfo.getProductId())).thenReturn(true);

        GeneralResponse response = shopKartProductService.deleteProduct(productInfo);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Product deleted successfully").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void deleteProductProductNotExistsTest()
    {
        productInfo.setProductId(3);

        when(productDetailsRepository.existsById(productInfo.getProductId())).thenReturn(false);

        GeneralResponse response = shopKartProductService.deleteProduct(productInfo);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.NOT_FOUND.value())).statusDesc("Product with id "+productInfo.getProductId()+" does not exist").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void deleteProductProductExceptionTest()
    {
        productInfo.setProductId(3);

        when(productDetailsRepository.existsById(productInfo.getProductId())).thenThrow(new RuntimeException());

        GeneralResponse response = shopKartProductService.deleteProduct(productInfo);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void deleteProductWrongProductIdTest()
    {
        productInfo.setProductId(0);

        GeneralResponse response = shopKartProductService.deleteProduct(productInfo);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Insufficient data").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void getProductsSuccessTest()
    {
        List<ProductInfo> products = new ArrayList<>();
        products.add(new ProductInfo(1,"Knife",20.8,"Green","Best knife to stab someone",90));
        products.add(new ProductInfo(2,"Red Knife",20.0,"Red","Best knife to stab someone",20));
        products.add(new ProductInfo(3,"Green Knife",60.2,"Blue","Best knife to stab someone",20));

        when(productDetailsRepository.findAll()).thenReturn(products);

        GeneralResponse response = shopKartProductService.getProducts();
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Products fetched successfully").products(products).build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void getProductsEmptyArrayTest()
    {
        List<ProductInfo> products = new ArrayList<>();
        when(productDetailsRepository.findAll()).thenReturn(products);

        GeneralResponse response = shopKartProductService.getProducts();
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Products fetch failed").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void getProductsExceptionTest()
    {
        when(productDetailsRepository.findAll()).thenThrow(new RuntimeException());

        GeneralResponse response = shopKartProductService.getProducts();
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void updateProductNullTest()
    {
        GeneralResponse response = shopKartProductService.updateProduct(null, productRequestDTO);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("No product Id entered").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void updateProductNotNullTest()
    {
        productInfo.setProductId(2);
        productInfo.setProductName("new product name");
        productInfo.setProductColor("new color");
        productInfo.setProductCost(20.0);
        productInfo.setDescription("New description");
        productInfo.setNumOfProd(3);

        when(productDetailsRepository.existsById(productInfo.getProductId())).thenReturn(true);
        when(productDetailsRepository.save(productInfo)).thenReturn(productInfo);

        GeneralResponse response = shopKartProductService.updateProduct(productInfo, productRequestDTO);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Product updated successfully").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void updateProductInsufficientDataTest()
    {
        productInfo.setProductId(2);

        when(productDetailsRepository.existsById(productInfo.getProductId())).thenReturn(true);

        GeneralResponse response = shopKartProductService.updateProduct(productInfo, productRequestDTO);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Insufficient data").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void updateProductWrongProductIdTest()
    {
        productInfo.setProductId(2);

        when(productDetailsRepository.existsById(productInfo.getProductId())).thenReturn(false);

        GeneralResponse response = shopKartProductService.updateProduct(productInfo, productRequestDTO);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("No product exists with ID "+productInfo.getProductId()).build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }

    @Test
    public void updateProductExceptionTest()
    {
        productInfo.setProductId(2);

        when(productDetailsRepository.existsById(productInfo.getProductId())).thenThrow(new RuntimeException());

        GeneralResponse response = shopKartProductService.updateProduct(productInfo, productRequestDTO);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
        Assertions.assertEquals(expected.getProducts(), response.getProducts());
    }
}
