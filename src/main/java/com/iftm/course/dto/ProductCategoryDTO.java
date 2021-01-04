package com.iftm.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iftm.course.entities.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDTO implements Serializable {

    private static final long serialVersionUID = 1426463318337766739L;

    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    @JsonProperty("categories")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductCategoryDTO() {
    }

    public ProductCategoryDTO(String name, String description, Double price, String imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductCategoryDTO(Product entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public Product toEntity(){
        return new Product(null,name,description,price,imgUrl);
    }
}
