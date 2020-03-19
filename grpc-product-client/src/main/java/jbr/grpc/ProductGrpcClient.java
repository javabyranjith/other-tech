package jbr.grpc;

import jbr.grpc.product.ProductRequest;
import jbr.grpc.product.ProductResponse;
import jbr.grpc.product.ProductServiceGrpc;
import jbr.grpc.product.ProductServiceGrpc.ProductServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductGrpcClient {
  public static void main(String[] args) {

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6070)
        .usePlaintext(true)
        .build();
    System.out.println("wewe");
    ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);
    ProductResponse response = stub.getProduct(ProductRequest.newBuilder()
        .setName("laptop")
        .build());

    System.out.println(response);

    channel.shutdown();
  }
}
