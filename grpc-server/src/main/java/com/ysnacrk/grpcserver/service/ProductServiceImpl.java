package com.ysnacrk.grpcserver.service;

import com.ysnacrk.grpcserver.*;
import io.grpc.stub.StreamObserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    private Connection connection;

    @Override
    public void addProduct(AddProductRequest request, StreamObserver<AddProductResponse> responseObserver) {

        logger.info("--- AddProduct called ---");

        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        Random random = new Random();

        AddProductResponse addProductResponse = AddProductResponse
                .newBuilder().
                setId(String.valueOf(random.nextInt())).
                build();


        try {
            connection.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        responseObserver.onNext(addProductResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<Product> responseObserver) {
        logger.info("--- GetProduct called ---");

        Random random = new Random();
        Product product = Product.newBuilder().setId(String.valueOf(random.nextInt())).build();
        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }
}
