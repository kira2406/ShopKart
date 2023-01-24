package com.ecommerce.Shopkart.Service;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;

public interface ShopKartUserService {

    UserDetails userRegister(UserDetails userDetails);

    UserDetails loadUserByUsername(String userId);
}
