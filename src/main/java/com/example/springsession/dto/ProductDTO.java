package com.example.springsession.dto;

public class ProductDTO {
    private String title;
    private boolean inStock;
    private String description;
    private int salePrice;



    public boolean isInStock() {
        return inStock;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
