package com.example.springsession.service;

import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.ProductResponseDTO;

public interface Service {
    ProductResponseDTO searchProduct(ProductRequestDTO productRequest);

}
