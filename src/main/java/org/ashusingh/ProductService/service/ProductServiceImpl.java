package org.ashusingh.ProductService.service;

import lombok.extern.log4j.Log4j2;
import org.ashusingh.ProductService.entity.Product;
import org.ashusingh.ProductService.exception.ProductServiceCustomException;
import org.ashusingh.ProductService.model.ProductRequest;
import org.ashusingh.ProductService.model.ProductResponse;
import org.ashusingh.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.beans.BeanUtils.copyProperties;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository  productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product...");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product Created...");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productId : {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);
        return productResponse;
    }
}
