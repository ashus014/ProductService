package org.ashusingh.ProductService.service;

import org.ashusingh.ProductService.model.ProductRequest;
import org.ashusingh.ProductService.model.ProductResponse;
import org.springframework.stereotype.Service;


public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
}
