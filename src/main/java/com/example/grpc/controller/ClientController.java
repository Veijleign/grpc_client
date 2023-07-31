package com.example.grpc.controller;

import com.example.grpc.internal.RouteGuideClient;
import com.example.grpc.payload.PokemonDTO;
import com.example.pokemon.Empty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
public class ClientController {

    private final RouteGuideClient client;

    @GetMapping("/hello")
    public ResponseEntity<String> greeting(
            @RequestParam String name
    ) {
        return ResponseEntity.ok(client.get(name));
    }

    @PostMapping("/create")
    public ResponseEntity<PokemonDTO> createPokemon(
            @RequestBody PokemonDTO dto
    ) {
        return ResponseEntity.ok(client.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(client.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PokemonDTO>> getAll() {
        return ResponseEntity.ok(client.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePokemon(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(client.deleteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PokemonDTO> updateById(
            @PathVariable Long id,
            @RequestBody PokemonDTO dto
    ) {
        return ResponseEntity.ok(client.updateById(id, dto));
    }

}
