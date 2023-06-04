package com.luckycart.luckyCart.service;

import com.luckycart.luckyCart.model.ProductRequest;
import com.luckycart.luckyCart.model.ProductResponse;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long productId);

    void reduceQuantity(long productId, long quantity);
}