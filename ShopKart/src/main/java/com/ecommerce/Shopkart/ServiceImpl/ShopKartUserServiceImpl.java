package com.ecommerce.Shopkart.ServiceImpl;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;
import com.ecommerce.Shopkart.Exception.ControllerException;
import com.ecommerce.Shopkart.Exception.UsernameNotFoundException;
import com.ecommerce.Shopkart.Repo.UserDetailsRepository;
import com.ecommerce.Shopkart.Service.ShopKartUserService;
import com.ecommerce.Shopkart.Util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class ShopKartUserServiceImpl implements ShopKartUserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    /*
    * @param userDetails
    * @return
    * */
    @Override
    public GeneralResponse userLogin(UserDetails userDetails) {
    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

        try {
            log.info(methodName + "has invoked");
            if (userDetails != null && userDetails.getUserId() != null && !userDetails.getUserId().isEmpty() && userDetails.getPassword() != null && !userDetails.getPassword().isEmpty() && !userDetails.getUserId().equalsIgnoreCase("null") && !userDetails.getUserId().equalsIgnoreCase("na") && !userDetails.getPassword().equalsIgnoreCase("null") && !userDetails.getPassword().equalsIgnoreCase("na")) {
                UserDetails userDetailsRepo = userDetailsRepository.findByUserId(userDetails.getUserId());
                if (userDetailsRepo != null && userDetailsRepo.getPassword().equals(userDetails.getPassword())) {
                    log.info("Login Successful");
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Login Successful").build();
                } else {
                    log.info("Login Failed");
                    return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Login Failed").build();
                }
            } else {
                return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.NOT_FOUND.value())).statusDesc("Incorrect Data").build();
            }
        } catch (Exception e) {
            log.error("Exception "+e.getMessage());
            return GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        }
    }

    @Override
    public UserDetails userRegister(UserDetails userDetails) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+" has invoked");
            if(Util.stringNullChecker(userDetails.getUserId()) && Util.stringNullChecker(userDetails.getPassword()) && Util.stringNullChecker(userDetails.getEmailId()) && Util.stringNullChecker(userDetails.getName()))
            {
                    log.info("Registered Successfully");
                    return userDetailsRepository.save(userDetails);
            }
            else{
                throw new ControllerException("Insufficient Data");
            }

        } catch (Exception e) {
            log.error("Exception "+e.getMessage());
            throw new ControllerException(e.getMessage());
        }

    }

    @Override
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        try {
            return userDetailsRepository.findByUserId(userId);
        }
        catch(Exception e)
        {
            throw new UsernameNotFoundException("User not found with the given username");
        }

    }
}
