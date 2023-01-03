package com.ysnacrk.grpcserver.service;

import com.ysnacrk.grpcserver.AddProductRequest;
import com.ysnacrk.grpcserver.AddProductResponse;
import com.ysnacrk.grpcserver.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.Random;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {
    @Override
    public void addProduct(AddProductRequest request, StreamObserver<AddProductResponse> responseObserver) {
        Random random = new Random();
        AddProductResponse addProductResponse = AddProductResponse
                .newBuilder().
                setId(String.valueOf(random.nextInt())).
                build();

        responseObserver.onNext(addProductResponse);
        responseObserver.onCompleted();
    }
}
