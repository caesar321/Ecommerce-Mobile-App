package com.example.e_commerceapp;

public class AbouItemInfoModel {
    private String brand,material,condition,weight,preview;

    public AbouItemInfoModel(String brand, String material, String condition, String weight, String preview) {
        this.brand = brand;
        this.material = material;
        this.condition = condition;
        this.weight = weight;
        this.preview = preview;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
