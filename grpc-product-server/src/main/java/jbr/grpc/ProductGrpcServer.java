package jbr.grpc;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jbr.grpc.product.ProductServiceImpl;

public class ProductGrpcServer {
  public static void main(String[] args) throws IOException, InterruptedException {

    Server server = ServerBuilder.forPort(6070)
        .addService(new ProductServiceImpl())
        .build();
    server.start();

    server.awaitTermination();
  }
}
