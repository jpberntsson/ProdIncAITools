package com.jpberntsson.tariffmodule.repository;

import com.jpberntsson.tariffmodule.domain.Tariff;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface TariffRepository extends MongoRepository<Tariff, String> {
    // The basic CRUD operations are inherited from MongoRepository.
    // Here's how you can define custom find (read) and delete methods using the Tariff's id.

    // Find a Tariff by its ID
    Optional<Tariff> findById(String id);

    // Delete a Tariff by its ID (the method exists in MongoRepository, shown here for clarity)
    void deleteById(String id);


}
