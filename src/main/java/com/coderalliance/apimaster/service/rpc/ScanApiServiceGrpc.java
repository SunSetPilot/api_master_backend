package com.coderalliance.apimaster.service.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: api_scanner.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ScanApiServiceGrpc {

  private ScanApiServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.coderalliance.apimaster.service.rpc.ScanApiService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest,
      com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse> getScanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Scan",
      requestType = com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest.class,
      responseType = com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest,
      com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse> getScanMethod() {
    io.grpc.MethodDescriptor<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest, com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse> getScanMethod;
    if ((getScanMethod = ScanApiServiceGrpc.getScanMethod) == null) {
      synchronized (ScanApiServiceGrpc.class) {
        if ((getScanMethod = ScanApiServiceGrpc.getScanMethod) == null) {
          ScanApiServiceGrpc.getScanMethod = getScanMethod =
              io.grpc.MethodDescriptor.<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest, com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Scan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ScanApiServiceMethodDescriptorSupplier("Scan"))
              .build();
        }
      }
    }
    return getScanMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ScanApiServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ScanApiServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ScanApiServiceStub>() {
        @java.lang.Override
        public ScanApiServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ScanApiServiceStub(channel, callOptions);
        }
      };
    return ScanApiServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ScanApiServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ScanApiServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ScanApiServiceBlockingStub>() {
        @java.lang.Override
        public ScanApiServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ScanApiServiceBlockingStub(channel, callOptions);
        }
      };
    return ScanApiServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ScanApiServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ScanApiServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ScanApiServiceFutureStub>() {
        @java.lang.Override
        public ScanApiServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ScanApiServiceFutureStub(channel, callOptions);
        }
      };
    return ScanApiServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void scan(com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest request,
        io.grpc.stub.StreamObserver<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getScanMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ScanApiService.
   */
  public static abstract class ScanApiServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ScanApiServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ScanApiService.
   */
  public static final class ScanApiServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ScanApiServiceStub> {
    private ScanApiServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScanApiServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ScanApiServiceStub(channel, callOptions);
    }

    /**
     */
    public void scan(com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest request,
        io.grpc.stub.StreamObserver<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getScanMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ScanApiService.
   */
  public static final class ScanApiServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ScanApiServiceBlockingStub> {
    private ScanApiServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScanApiServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ScanApiServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse scan(com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getScanMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ScanApiService.
   */
  public static final class ScanApiServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ScanApiServiceFutureStub> {
    private ScanApiServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScanApiServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ScanApiServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse> scan(
        com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getScanMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SCAN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SCAN:
          serviceImpl.scan((com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest) request,
              (io.grpc.stub.StreamObserver<com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getScanMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiRequest,
              com.coderalliance.apimaster.service.rpc.ApiScanner.ScanApiResponse>(
                service, METHODID_SCAN)))
        .build();
  }

  private static abstract class ScanApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ScanApiServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.coderalliance.apimaster.service.rpc.ApiScanner.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ScanApiService");
    }
  }

  private static final class ScanApiServiceFileDescriptorSupplier
      extends ScanApiServiceBaseDescriptorSupplier {
    ScanApiServiceFileDescriptorSupplier() {}
  }

  private static final class ScanApiServiceMethodDescriptorSupplier
      extends ScanApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ScanApiServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ScanApiServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ScanApiServiceFileDescriptorSupplier())
              .addMethod(getScanMethod())
              .build();
        }
      }
    }
    return result;
  }
}
