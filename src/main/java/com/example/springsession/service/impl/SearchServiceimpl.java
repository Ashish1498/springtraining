package com.example.springsession.service.impl;

import com.example.springsession.dto.ProductDTO;
import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.ProductResponseDTO;
import com.example.springsession.service.Service;

import java.util.Arrays;
@org.springframework.stereotype.Service
public class SearchServiceimpl implements Service {

    public ProductResponseDTO searchProduct(ProductRequestDTO request){
        ProductResponseDTO responseDTO=new ProductResponseDTO();
        ProductDTO product=new ProductDTO();
        product.setDescription("Apple iphone 11 pro..");
        product.setTitle("Mobiles");
        product.setInStock(true);
        responseDTO.setProducts(Arrays.asList(product));
        return responseDTO;
    }


}
