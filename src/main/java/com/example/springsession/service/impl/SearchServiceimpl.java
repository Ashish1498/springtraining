package com.example.springsession.service.impl;

import com.example.springsession.client.SearchClient;
import com.example.springsession.dto.ProductDTO;
import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.ProductResponseDTO;
import com.example.springsession.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@org.springframework.stereotype.Service

public class SearchServiceimpl implements Service {


    @Autowired
    private SearchClient searchClient;

    @Override

    public ProductResponseDTO searchProduct(ProductRequestDTO request) {

        Map<String, Object> products = searchClient.getProducts(request.getSearchTerm());

        ExecutorService executor = Executors.newFixedThreadPool(2);
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        Runnable task = () -> {
            List<Map<String, Object>> productObjectList = (List<Map<String, Object>>) ((Map) products.get("response")).get("docs");


            List<ProductDTO> list = new ArrayList<>();
            for (int i = 0; i < productObjectList.size(); i++) {
                ProductDTO product = new ProductDTO();
                String brandName = productObjectList.get(i).get("name").toString();
                String brandDescription = productObjectList.get(i).get("description").toString();
                int brandPrice = ((Double) productObjectList.get(i).get("salePrice")).intValue();
                boolean inStock = (int) productObjectList.get(i).get("isInStock") == 1 ? true : false;

                product.setTitle(brandName);
                product.setDescription(brandDescription);
                product.setSalePrice(brandPrice);
                product.setInStock(inStock);

                list.add(product);
                responseDTO.setProducts(list);


            }

        };

        Runnable task2 = () -> {
            String query = "stockLocation:\"" + request.getLocationBasedProduct() + "\"";
            Map<String, Object> location = searchClient.getProducts(query);
            List<Map<String, Object>> locationObjectList = (List<Map<String, Object>>) ((Map) location.get("response")).get("docs");


            List<ProductDTO> list2 = new ArrayList<>();
            for (int i = 0; i < locationObjectList.size(); i++) {
                ProductDTO product = new ProductDTO();
                String brandName = locationObjectList.get(i).get("name").toString();
                String brandDescription = locationObjectList.get(i).get("description").toString();
                int brandPrice = ((Double) locationObjectList.get(i).get("salePrice")).intValue();
                boolean inStock = (int) locationObjectList.get(i).get("isInStock") == 1 ? true : false;

                product.setTitle(brandName);
                product.setDescription(brandDescription);
                product.setSalePrice(brandPrice);
                product.setInStock(inStock);

                list2.add(product);
                responseDTO.setLocationBasedProducts(list2);
                System.out.println(Thread.currentThread().getId());

            }
        };
        executor.execute(task);
        executor.execute(task2);
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseDTO;
    }




}


