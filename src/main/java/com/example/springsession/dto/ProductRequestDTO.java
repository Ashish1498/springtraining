package com.example.springsession.dto;

import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;

import java.util.ArrayList;

public class ProductRequestDTO {

    private String searchTerm;
    private String locationBasedProduct;

    public String getLocationBasedProduct() {
        return locationBasedProduct;
    }

    public void setLocationBasedProduct(String locationBasedProduct) {
        this.locationBasedProduct = locationBasedProduct;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }


}
