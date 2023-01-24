package com.ecommerce.Shopkart.Dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponse<T> {

    private String status;
    private List<ErrorResponseDTO> errors;
    private T data;
}
