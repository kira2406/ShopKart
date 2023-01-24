package com.ecommerce.Shopkart.Controller;

import com.ecommerce.Shopkart.Dto.UserCredentials;
import com.ecommerce.Shopkart.Dto.UserDetails;
import com.ecommerce.Shopkart.Exception.ControllerException;
import com.ecommerce.Shopkart.Exception.LoginFailedException;
import com.ecommerce.Shopkart.Service.ShopKartUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ShopKartUserController {

    @Autowired
    ShopKartUserService shopKartUserService;

    @PostMapping("/authLogin")
    public ResponseEntity<?> userLogin(@Validated @RequestBody UserCredentials userCredentials){

            try {

                final UserDetails userDetails = shopKartUserService.loadUserByUsername(userCredentials.getUsername());

                if (userDetails.getPassword().equals(userCredentials.getPassword())) {
                    return new ResponseEntity<>(userDetails, HttpStatus.OK);
                } else {
                    throw new LoginFailedException("login failed");
                }
            }
            catch(LoginFailedException e)
            {
                throw new LoginFailedException("Login failed due to incorrect password");
            } catch(Exception e)
            {
                throw new ControllerException("Data Insufficient");
            }

    }

    @PostMapping("/authRegister")
    public ResponseEntity<?> userRegister( @RequestBody @Validated UserDetails userDetails){

        try {
            UserDetails userDetailsRepo = shopKartUserService.userRegister(userDetails);
            return new ResponseEntity<>(userDetailsRepo, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            throw new ControllerException("Data Insufficient");
        }
    }
}
