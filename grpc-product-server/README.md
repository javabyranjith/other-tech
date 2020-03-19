### TOOLS & TECHNOLOGIES
  1. Eclipse/STS
  2. Java 1.8

### CONCEPTS/TOPICS COVERED
  1. GRPC

### HOW TO RUN?
  1. pom.xml -> Run As - Maven build -> add "compile" goal
    now all the compilation issues, from ProductServiceImpl, will be resolved
  2. pom.xml -> Run As - Maven install
  3. copy the jar target/grpc-product-server.jar to client
  4. ProductGrpcServer.java -> Run As -> Java Application
