package jbr.grpc.product;

import io.grpc.stub.StreamObserver;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

  @Override
  public void getProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

    System.out.println(request);

    responseObserver.onNext(ProductResponse.newBuilder()
        .setProduct("Product: " + request.getName())
        .build());

    responseObserver.onCompleted();
  }
}
