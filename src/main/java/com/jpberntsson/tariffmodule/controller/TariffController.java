package com.jpberntsson.tariffmodule.controller;

import com.jpberntsson.tariffmodule.domain.Tariff;
import com.jpberntsson.tariffmodule.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tariffs")
public class TariffController {

    private final TariffRepository tariffRepository;

    @Autowired
    public TariffController(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    // Create a new Tariff
    @PostMapping
    public Tariff createTariff(@RequestBody Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    // Get all Tariffs
    @GetMapping
    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }

    // Get a single Tariff by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tariff> getTariffById(@PathVariable String id) {
        Optional<Tariff> tariff = tariffRepository.findById(id);
        return tariff.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Tariff
    @PutMapping("/{id}")
    public ResponseEntity<Tariff> updateTariff(@PathVariable String id, @RequestBody Tariff tariffDetails) {
        Optional<Tariff> tariffData = tariffRepository.findById(id);

        if (tariffData.isPresent()) {
            Tariff updatedTariff = tariffData.get();
            updatedTariff.setCurrency(tariffDetails.getCurrency());
            // Set other properties from tariffDetails to updatedTariff as needed

            Tariff savedTariff = tariffRepository.save(updatedTariff);
            return ResponseEntity.ok(savedTariff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Tariff
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTariff(@PathVariable String id) {
        try {
            tariffRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting tariff with ID " + id);
        }
    }
}

