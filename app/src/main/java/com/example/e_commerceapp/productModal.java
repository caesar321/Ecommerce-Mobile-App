package com.example.e_commerceapp;

public class productModal {
    int id,quantity;
    double price,rating;
    String title,image,description,Category,brand,material,condition,weight,preview;

    public productModal() {

    }

    public productModal(int id, double price, double rating, String title, String image, String description, String category, String brand, String material, String condition, String weight, String preview) {
        this.id = id;

        this.price = price;
        this.rating = rating;
        this.title = title;
        this.image = image;
        this.description = description;
        Category = category;
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


    public void increamentQuantity(){
        quantity++;
}
public double getTotalPrice(){
        return price * quantity;
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
