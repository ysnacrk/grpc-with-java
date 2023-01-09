package com.ysnacrk.grpcserver;


import com.ysnacrk.grpcserver.repository.ProductRepository;
import com.ysnacrk.grpcserver.service.ProductServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder
				.forPort(8081)
				.addService(new ProductServiceImpl())
				.build();

		ProductRepository productRepository = new ProductRepository();
		productRepository.printProductList();

		server.start();

		System.out.println("gRPC server started port : " + server.getPort());
		server.awaitTermination();
	}

}
