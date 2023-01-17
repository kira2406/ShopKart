package com.ecommerce.Shopkart.Controller;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.ProductInfo;
import com.ecommerce.Shopkart.Service.ShopKartProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class ShopKartProductController {

    @Autowired
    ShopKartProductService shopKartProductService;


    @PostMapping("/addProduct")
    public GeneralResponse addProduct(@RequestBody ProductInfo productInfo){
        return shopKartProductService.addProduct(productInfo);
    }

    @PostMapping("/deleteProduct")
    public GeneralResponse deleteProduct(@RequestParam Integer productId){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        return shopKartProductService.deleteProduct(productInfo);
    }

    @GetMapping("/getProducts")
    public GeneralResponse getProducts(){
        return shopKartProductService.getProducts();
    }

    @PostMapping("/updateProduct")
    public GeneralResponse updateProduct(@RequestBody ProductInfo productInfo){
        return shopKartProductService.updateProduct(productInfo);
    }
}
