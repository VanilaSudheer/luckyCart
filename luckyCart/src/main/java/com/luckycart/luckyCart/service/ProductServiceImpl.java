package com.luckycart.luckyCart.service;

import com.luckycart.luckyCart.entity.Product;
import com.luckycart.luckyCart.exception.ProductServiceCustomException;
import com.luckycart.luckyCart.model.ProductRequest;
import com.luckycart.luckyCart.model.ProductResponse;
import com.luckycart.luckyCart.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2

public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("Adding Product ...");
        Product product
                = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product saved");
        return product.getProductId();

    }
    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Get the Product for the ProductId:{} ",productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product,productResponse);
        return productResponse;
    }
    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reducing quantity {} from productId {}",quantity,productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException("Product does not have sufficient quatity","INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product quantity updated successfully");

    }


}
