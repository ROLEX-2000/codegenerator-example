package com.demo.brandservice.dto;

public class BrandDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String name;
    private String type;
    private double price;

    public BrandDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}