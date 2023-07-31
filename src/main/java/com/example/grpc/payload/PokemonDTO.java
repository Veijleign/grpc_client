package com.example.grpc.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDTO {
    private Long id;

    private String name;

    private boolean isActive;
}