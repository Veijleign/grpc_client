package com.example.grpc.internal;

import com.example.grpc.payload.PokemonDTO;
import com.example.pokemon.*;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteGuideClient {

    private final PokemonServiceGrpc.PokemonServiceBlockingStub blockingStub;

    public RouteGuideClient() {
        this.blockingStub = PokemonServiceGrpc
                .newBlockingStub(ManagedChannelBuilder
                        .forAddress("localhost", 9090)
                        .usePlaintext()
                        .build());
    }

    public String get(String name) {
        HelloResponse response = blockingStub.greeting(HelloRequest
                .newBuilder()
                .setName(name)
                .build());
        return response.getMessage();
    }

    public PokemonDTO create(PokemonDTO dto) {
        PokemonResponse response = blockingStub.create(
                CreatePokemonRequest
                        .newBuilder()
                        .setName(dto.getName())
                        .setIsActive(dto.isActive())
                        .build());
        return new PokemonDTO(
                response.getId(),
                response.getName(),
                response.getIsActive()
        );
    }

    public PokemonDTO getById(Long id) {
        PokemonResponse response = blockingStub.getOne(
                GetPokemonRequest
                        .newBuilder()
                        .setId(id)
                        .build());

        return new PokemonDTO(
                response.getId(),
                response.getName(),
                response.getIsActive()
        );
    }

    public List<PokemonDTO> getAll() {

        Empty empty = Empty.newBuilder().build();

        PokemonResponses response = blockingStub.getAll(empty);

        List<PokemonResponse> responseList = response.getResponseList();

        List<PokemonDTO> listOfPokemon = new ArrayList<>();

        for (PokemonResponse responses : responseList) {
            listOfPokemon.add(new PokemonDTO(
                    responses.getId(),
                    responses.getName(),
                    responses.getIsActive()
            ));
        }

        return listOfPokemon;
    }

    public Void deleteById(Long id) {

        blockingStub.delete(
                GetPokemonRequest
                        .newBuilder()
                        .setId(id)
                        .build());

        return null;
    }

    public PokemonDTO updateById(Long id, PokemonDTO dto) {

        PokemonResponse response = blockingStub.update(
                UpdatePokemonRequest.newBuilder()
                        .setId(id)
                        .setName(dto.getName())
                        .setIsActive(dto.isActive())
                        .build());

        return new PokemonDTO(
                response.getId(),
                response.getName(),
                response.getIsActive()
        );
    }
}

