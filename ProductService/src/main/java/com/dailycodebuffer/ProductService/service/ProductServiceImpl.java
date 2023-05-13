package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.Product;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepository;
import com.dailycodebuffer.ProductService.exception.ProductServiceCustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    Logger log = LogManager.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {

        log.info(" ProductService: addProduct execution started ... ");

        try {
            log.info(" ProductService: addProduct request payload {} ", new ObjectMapper().writeValueAsString(productRequest));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        try {

            log.info(" ProductService: addProduct response {} ", new ObjectMapper().writeValueAsString(productRequest));


        } catch (JsonProcessingException e) {
            log.info("ProductService: addProduct execution denied ...{} ", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            return product.getProductId();
        }


    }

    @Override
    public ProductResponse getProductById(Long productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() -> new ProductServiceCustomException("Produto nao encontrado com o id informado ",
                                "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse
                = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        System.out.println(" productId : " + productId + " quantity : " + quantity);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Produto nao encontrado ",
                        "PRODUCT_NOT_FOUND"));

        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Produto nao tem a quantidade suficiente ",
                    "INSUFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }


}
