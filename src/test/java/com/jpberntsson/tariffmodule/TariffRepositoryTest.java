package com.jpberntsson.tariffmodule;

import com.jpberntsson.tariffmodule.domain.Tariff;
import com.jpberntsson.tariffmodule.repository.TariffRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class TariffRepositoryTest {

    @Autowired
    private TariffRepository tariffRepository;

    @Test
    public void testCreateTariff() {
        // Create a Tariff object
        Tariff tariff = new Tariff();
        tariff.setId("1");
        tariff.setCurrency("EUR");
        // Add other necessary properties to the Tariff object

        // Save the Tariff
        Tariff savedTariff = tariffRepository.save(tariff);

        // Retrieve the Tariff
        Tariff foundTariff = tariffRepository.findById(savedTariff.getId()).orElse(null);

        // Verify the Tariff was saved correctly
        assertThat(foundTariff).isNotNull();
        assertThat(foundTariff.getId()).isEqualTo(tariff.getId());
        assertThat(foundTariff.getCurrency()).isEqualTo(tariff.getCurrency());
        // Add other assertions as necessary
    }

    @Test
    public void testDeleteTariff() {
        // Create and save a Tariff object
        Tariff tariff = new Tariff();
        tariff.setId("2");
        tariff.setCurrency("USD");
        // Set other properties
        Tariff savedTariff = tariffRepository.save(tariff);

        // Delete the Tariff
        tariffRepository.deleteById(savedTariff.getId());

        // Try to retrieve the deleted Tariff
        Optional<Tariff> deletedTariff = tariffRepository.findById(savedTariff.getId());

        // Verify the Tariff was deleted
        assertThat(deletedTariff.isPresent()).isFalse();
    }

    @Test
    public void testUpdateTariff() {
        // Create and save a Tariff object
        Tariff tariff = new Tariff();
        tariff.setId("3");
        tariff.setCurrency("GBP");
        // Set other properties
        Tariff savedTariff = tariffRepository.save(tariff);

        // Update the Tariff
        savedTariff.setCurrency("EUR"); // Example update
        Tariff updatedTariff = tariffRepository.save(savedTariff);

        // Retrieve the updated Tariff
        Tariff foundTariff = tariffRepository.findById(updatedTariff.getId()).orElse(null);

        // Verify the Tariff was updated
        assertThat(foundTariff).isNotNull();
        assertThat(foundTariff.getCurrency()).isEqualTo("EUR");
        // Verify other properties if necessary
    }

}
