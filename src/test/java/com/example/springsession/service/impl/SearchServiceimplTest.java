package com.example.springsession.service.impl;

import com.example.springsession.client.SearchClient;
import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.ProductResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
class SearchServiceimplTest {


    @InjectMocks
    private SearchServiceimpl searchService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach //it willl  execute this method before @test method get executed everytime
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown(){

    }

    @Test
    void searchProduct() throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> searchTermMockObject=objectMapper.readValue(new URL("file:src/main/resources/search.mock"),Map.class);
        Map<String,Object> locationMockObject=objectMapper.readValue(new URL("file:src/main/resources/location.mock"),Map.class);
        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:\"jakarta\"")).thenReturn(locationMockObject);

        ProductRequestDTO requestDTO=new ProductRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setLocationBasedProduct("jakarta");
        ProductResponseDTO responseDTO=searchService.searchProduct(requestDTO);

        assertEquals(responseDTO.getProducts().size(),10);
        assertEquals(responseDTO.getLocationBasedProducts().size(),10);
    }

    @Test
    public void testGetProductsExceptionTest() throws IOException{
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> searchTermMockObject=objectMapper.readValue(new URL("file:src/main/resources/search.mock"),Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:\"jakarta\"")).thenThrow(NullPointerException.class);

        ProductRequestDTO requestDTO=new ProductRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setLocationBasedProduct("jakarta");
        ProductResponseDTO responseDTO=searchService.searchProduct(requestDTO);

        assertEquals(responseDTO.getProducts().size(),10);
        assertEquals(responseDTO.getLocationBasedProducts(),null);

        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:\"jakarta\"");

    }
}