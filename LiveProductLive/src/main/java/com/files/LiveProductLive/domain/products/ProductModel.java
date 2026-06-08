package com.files.LiveProductLive.domain.products;

import java.util.UUID;

public class ProductModel {
    private UUID id;
    private String name;
    private Float price;
    private String url;

    public static ProductModel createNew(UUID id, String name, Float price, String url) {
        return new ProductModel(id, name, price, url);
    }

    public static ProductModel createDraft(String name, Float price, String url) {
        return new ProductModel(UUID.randomUUID(), name, price, url);
    }

    public ProductModel(UUID id, String name, Float price, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
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

    public String getUrl() {
        return url;
    }
}
