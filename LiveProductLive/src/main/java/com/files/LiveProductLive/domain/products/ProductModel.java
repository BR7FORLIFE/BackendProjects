package com.files.LiveProductLive.domain.products;

import java.util.UUID;

public class ProductModel {
    private UUID id;
    private String name;
    private Float price;

    public static ProductModel createNew(UUID id, String name, Float price) {
        return new ProductModel(id, name, price);
    }

    public static ProductModel createDraft(String name, Float price) {
        return new ProductModel(UUID.randomUUID(), name, price);
    }

    public ProductModel(UUID id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
