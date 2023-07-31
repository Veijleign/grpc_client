package com.example.grpc.service;

import com.example.pokemon.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class ServiceGrpc
        extends PokemonServiceGrpc.PokemonServiceImplBase {

    private final ManagedChannel managedChannel;

    public ServiceGrpc() {
        managedChannel =
                ManagedChannelBuilder.forAddress(
                                "localhost",
                                9090)
                        .usePlaintext()
                        .build();
    }

    @Override
    public void greeting(HelloRequest request,
                         StreamObserver<HelloResponse> responseObserver) {
        // blocking stub
        PokemonServiceGrpc.PokemonServiceBlockingStub blockingStub =
                PokemonServiceGrpc.newBlockingStub(managedChannel);

        // не выполняется
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName(request.getName())
                .build();

        HelloResponse response = blockingStub.greeting(helloRequest);

        /*HelloResponse response = HelloResponse.newBuilder()
                .setMessage(request.getName())
                .build();*/

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void create(CreatePokemonRequest request,
                       StreamObserver<PokemonResponse> responseObserver) {

    }

    @Override
    public void getOne(GetPokemonRequest request,
                       StreamObserver<PokemonResponse> responseObserver) {
        //super.get(request, responseObserver);

        PokemonResponse response = PokemonResponse.newBuilder()
                .setId(1000)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Empty request,
                       StreamObserver<PokemonResponses> responseObserver) {
        super.getAll(request, responseObserver);
    }


    @Override
    public void update(UpdatePokemonRequest request,
                       StreamObserver<PokemonResponse> responseObserver) {
        super.update(request, responseObserver);
    }

    @Override
    public void delete(GetPokemonRequest request,
                       StreamObserver<Empty> responseObserver) {
        super.delete(request, responseObserver);
    }


}
