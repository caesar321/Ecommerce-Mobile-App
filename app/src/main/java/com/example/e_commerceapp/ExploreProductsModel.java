package com.example.e_commerceapp;

public class ExploreProductsModel {
    private String exProductName,exProductImage;

    public ExploreProductsModel(String exProductName, String exProductImage) {
        this.exProductName = exProductName;
        this.exProductImage = exProductImage;
    }

    public String getExProductName() {
        return exProductName;
    }

    public void setExProductName(String exProductName) {
        this.exProductName = exProductName;
    }

    public String getExProductImage() {
        return exProductImage;
    }

    public void setExProductImage(String exProductImage) {
        this.exProductImage = exProductImage;
    }
}
