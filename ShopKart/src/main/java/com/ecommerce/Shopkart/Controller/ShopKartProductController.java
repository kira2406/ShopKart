package com.ecommerce.Shopkart.Controller;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;
import com.ecommerce.Shopkart.Dto.ProductRequestDTO;
import com.ecommerce.Shopkart.Dto.ProductResponseDTO;
import com.ecommerce.Shopkart.Service.ShopKartProductService;
import com.ecommerce.Shopkart.Util.Util;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@Slf4j
@RestController
@CrossOrigin
public class ShopKartProductController {

    @Autowired
    ShopKartProductService shopKartProductService;


    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO){

        ProductResponseDTO productResponseDTO = shopKartProductService.addProduct(productRequestDTO);
        GeneralResponse<ProductResponseDTO> response = GeneralResponse
                .<ProductResponseDTO>builder()
                .status("SUCCESS")
                .data(productResponseDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        GeneralResponse<?> response = shopKartProductService.deleteProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){

        List<ProductResponseDTO> products = shopKartProductService.getProducts();
        GeneralResponse<List<ProductResponseDTO>> response = GeneralResponse
                .<List<ProductResponseDTO>>builder()
                .status("SUCCESS")
                .data(products)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getProducts/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Integer productId) {

        ProductResponseDTO productResponseDTO = shopKartProductService.getProductById(productId);
        GeneralResponse<ProductResponseDTO> response = GeneralResponse
                .<ProductResponseDTO>builder()
                .status("SUCCESS")
                .data(productResponseDTO)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updateProduct/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId,@RequestBody @Valid ProductRequestDTO productRequestDTO){
        GeneralResponse<?> response = shopKartProductService.updateProduct(productId,productRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
