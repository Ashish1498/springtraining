package com.example.springsession.controlller;

import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.ProductResponseDTO;
import com.example.springsession.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    Service productService;

    @PostMapping(value = "/search")
    public ProductResponseDTO searchMethod(@RequestBody ProductRequestDTO request){
        return productService.searchProduct(request);
    }


}
