package com.ecommerce.Shopkart.Service;

import com.ecommerce.Shopkart.Entity.UserDetails;

public interface ShopKartUserService {

    UserDetails userRegister(UserDetails userDetails);

    UserDetails loadUserByUsername(String userId);
}
