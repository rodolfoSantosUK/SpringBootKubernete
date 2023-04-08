package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.Product;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepository;
import com.dailycodebuffer.ProductService.exception.ProductServiceCustomException;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        System.out.println("Adicionando produto");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        return product.getProductId();
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
