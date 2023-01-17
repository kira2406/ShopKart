package com.ecommerce.Shopkart;

import com.ecommerce.Shopkart.Dto.GeneralResponse;
import com.ecommerce.Shopkart.Dto.UserDetails;
import com.ecommerce.Shopkart.Repo.UserDetailsRepository;
import com.ecommerce.Shopkart.ServiceImpl.ShopKartUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ShopKartUserServiceImplTests {

    @InjectMocks
    ShopKartUserServiceImpl shopKartUserService;

    @Mock
    UserDetailsRepository userDetailsRepository;

    UserDetails userDetails = new UserDetails();

    @Test
    public void userLoginNotNullTest()
    {
        userDetails.setUserId("Cabbage");
        userDetails.setPassword("Cabbage");
        when(userDetailsRepository.findByUserId(userDetails.getUserId())).thenReturn(userDetails);

        GeneralResponse response = shopKartUserService.userLogin(userDetails);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Login Successful").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }

    @Test
    public void userLoginNullTest()
    {
        GeneralResponse response = shopKartUserService.userLogin(null);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.NOT_FOUND.value())).statusDesc("Incorrect Data").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }

    @Test
    public void userLoginFailedTest()
    {
        userDetails.setUserId("Babbage");
        userDetails.setPassword("Cabbage");

        UserDetails userDetailsRepo = new UserDetails(1,"Babbage","Babbage","Babbage","babbage@gmail.com","89838383838");
        when(userDetailsRepository.findByUserId(userDetails.getUserId())).thenReturn(userDetailsRepo);

        GeneralResponse response = shopKartUserService.userLogin(userDetails);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Login Failed").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }

    @Test
    public void userLoginExceptionTest()
    {
        userDetails.setUserId("Babbage");
        userDetails.setPassword("Cabbage");

        when(userDetailsRepository.findByUserId(userDetails.getUserId())).thenThrow(new RuntimeException());

        GeneralResponse response = shopKartUserService.userLogin(userDetails);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }

    @Test
    public void userRegisterNullTest()
    {
        GeneralResponse response = shopKartUserService.userRegister(null);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Registration Failed").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }

    @Test
    public void userRegisterNotNullTest()
    {
        userDetails.setUserId("Cabbage");
        userDetails.setPassword("Cabbage");
        userDetails.setName("Cabbage");
        userDetails.setPhoneNo("9827272727");
        userDetails.setEmailId("cabbage@gmail.com");

        GeneralResponse response = shopKartUserService.userRegister(userDetails);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.OK.value())).statusDesc("Registered Successfully").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }

    @Test
    public void userRegisterExceptionTest()
    {
        userDetails.setUserId("Cabbage");
        userDetails.setPassword("Cabbage");
        userDetails.setName("Cabbage");
        userDetails.setPhoneNo("9827272727");
        userDetails.setEmailId("cabbage@gmail.com");

        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException());
        GeneralResponse response = shopKartUserService.userRegister(userDetails);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Something went wrong").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }

    @Test
    public void userRegisterInsufficientDataTest()
    {
        userDetails.setUserId("Cabbage");
        userDetails.setPassword(null);
        userDetails.setName("Cabbage");
        userDetails.setPhoneNo("9827272727");
        userDetails.setEmailId("cabbage@gmail.com");

        GeneralResponse response = shopKartUserService.userRegister(userDetails);
        GeneralResponse expected = GeneralResponse.builder().statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value())).statusDesc("Registration Failed").build();
        Assertions.assertEquals(expected.getStatusCode(), response.getStatusCode());
        Assertions.assertEquals(expected.getStatusDesc(), response.getStatusDesc());
    }
}
