package com.ecommerce.Shopkart.ServiceImpl;

import com.ecommerce.Shopkart.Entity.UserDetails;
import com.ecommerce.Shopkart.Exception.ControllerException;
import com.ecommerce.Shopkart.Exception.UsernameNotFoundException;
import com.ecommerce.Shopkart.Repo.UserDetailsRepository;
import com.ecommerce.Shopkart.Service.ShopKartUserService;
import com.ecommerce.Shopkart.Util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShopKartUserServiceImpl implements ShopKartUserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails userRegister(UserDetails userDetails) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            log.info(methodName+" has invoked");
            if(Util.stringNullChecker(userDetails.getUsername()) && Util.stringNullChecker(userDetails.getPassword()) && Util.stringNullChecker(userDetails.getEmailId()) && Util.stringNullChecker(userDetails.getName()))
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userDetailsRepository.findByUsername(username);
        }
        catch(Exception e)
        {
            throw new UsernameNotFoundException("User not found with the given username");
        }

    }
}
