package com.ecommerce.Shopkart.Controller;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;
import com.ecommerce.Shopkart.Service.ShopKartUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ShopKartUserController {

    @Autowired
    ShopKartUserService shopKartUserService;

    @PostMapping("/authLogin")
    public GeneralResponse userLogin(@RequestBody UserDetails userDetails){

//        UserDetails userDetails = new UserDetails();
//        userDetails.setUserId(userId);
//        userDetails.setPassword(password);
        return shopKartUserService.userLogin(userDetails);
    }

    @PostMapping("/authRegister")
    public GeneralResponse userRegister(@RequestBody UserDetails userDetails){
        return shopKartUserService.userRegister(userDetails);
    }
}
