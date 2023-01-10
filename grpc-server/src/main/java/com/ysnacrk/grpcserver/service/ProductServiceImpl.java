package com.ysnacrk.grpcserver.service;

import com.ysnacrk.grpcserver.*;
import com.ysnacrk.grpcserver.repository.ProductRepository;
import io.grpc.stub.StreamObserver;

import java.sql.Connection;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(AddProductRequest request, StreamObserver<AddProductResponse> responseObserver) {

        logger.info("--- AddProduct called ---");

        com.ysnacrk.grpcserver.entity.Product product = new com.ysnacrk.grpcserver.entity.Product();
        productRepository.save(product);

        AddProductResponse addProductResponse = AddProductResponse
                .newBuilder()
                .setId(String.valueOf(product.getId()))
                .build();

        responseObserver.onNext(addProductResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<Product> responseObserver) {

        logger.info("--- GetProduct called ---");

        com.ysnacrk.grpcserver.entity.Product product = productRepository.findById(Long.parseLong(request.getId()));

        Product resultProduct = Product
                .newBuilder()
                .setId(String.valueOf(product.getId()))
                .setCode(product.getCode())
                .setQuantity(String.valueOf(product.getQuantity()))
                .build();

        responseObserver.onNext(resultProduct);
        responseObserver.onCompleted();
    }
}
