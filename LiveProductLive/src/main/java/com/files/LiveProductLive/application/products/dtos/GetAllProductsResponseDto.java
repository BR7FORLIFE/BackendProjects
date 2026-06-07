package com.files.LiveProductLive.application.products.dtos;

import java.util.List;

import com.files.LiveProductLive.domain.products.ProductModel;

public record GetAllProductsResponseDto(List<ProductModel> products) {

}
