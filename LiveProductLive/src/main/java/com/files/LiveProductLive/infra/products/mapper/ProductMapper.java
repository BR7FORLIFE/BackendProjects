package com.files.LiveProductLive.infra.products.mapper;

import com.files.LiveProductLive.domain.products.ProductModel;
import com.files.LiveProductLive.infra.products.entity.ProductEntity;

public class ProductMapper {
    public static ProductModel toDomain(ProductEntity entity) {
        return ProductModel.createNew(entity.getId(), entity.getName(), entity.getPrice(), entity.getUrl());
    }

    public static ProductEntity toEntity(ProductModel model) {
        ProductEntity entity = new ProductEntity();

        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setPrice(model.getPrice());
        entity.setUrl(model.getUrl());

        return entity;
    }
}
