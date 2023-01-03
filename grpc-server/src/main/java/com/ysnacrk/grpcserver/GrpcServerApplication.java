package com.ysnacrk.grpcserver;


import com.ysnacrk.grpcserver.service.ProductServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder
				.forPort(8080)
				.addService(new ProductServiceImpl())
				.build();

		server.start();
		System.out.println("gRPC server started port : " + server.getPort());
		server.awaitTermination();
	}

}
