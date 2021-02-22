package com.example.springsession.service.impl;

import com.example.springsession.client.SearchClient;
import com.example.springsession.dto.ProductDTO;
import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.ProductResponseDTO;
import com.example.springsession.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class SearchServiceimpl implements Service {

    @Autowired
    private SearchClient searchClient;
    @Override

    public ProductResponseDTO searchProduct(ProductRequestDTO request){

        Map<String,Object> products=searchClient.getProducts(request.getSearchTerm());

        List<Map<String,Object>> productObjectList=(List<Map<String,Object>>) ((Map) products.get("response")).get("docs");
        ProductResponseDTO responseDTO=new ProductResponseDTO();;

        List<ProductDTO> list=new ArrayList<>();
        for(int i=0;i<productObjectList.size();i++)
        {
            ProductDTO product=new ProductDTO();
            String brandName=productObjectList.get(i).get("name").toString();
            String brandDescription=productObjectList.get(i).get("description").toString();
            int brandPrice= ((Double) productObjectList.get(i).get("salePrice")).intValue();
            boolean inStock=(int) productObjectList.get(i).get("isInStock") ==1? true:false;

            product.setTitle(brandName);
            product.setDescription(brandDescription);
            product.setSalePrice(brandPrice);
            product.setInStock(inStock);

            list.add(product);

        }
        responseDTO.setProducts(list);

        return responseDTO;
    }


}
