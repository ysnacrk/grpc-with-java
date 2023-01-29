package com.ysnacrk.grpcclient;

import com.ysnacrk.grpcserver.AddProductRequest;
import com.ysnacrk.grpcserver.AddProductResponse;
import com.ysnacrk.grpcserver.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductClient {

    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        ProductServiceGrpc.ProductServiceBlockingStub stub
                = ProductServiceGrpc.newBlockingStub(managedChannel);

        AddProductResponse response = stub.addProduct(
                AddProductRequest.newBuilder().
                        setCode("PR-101")
                        .setQuantity("2")
                        .build()
        );

        System.out.println(response.toString());
        managedChannel.shutdown();

    }
}
