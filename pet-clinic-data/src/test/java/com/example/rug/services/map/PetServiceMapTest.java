package com.example.rug.services.map;

import com.example.rug.model.Owner;
import com.example.rug.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Set;

class PetServiceMapTest {
    final Long id = 1L;
    PetServiceMap petServiceMap;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder()
                .name("Freddy")
                .owner(Owner.builder()
                        .id(id)
                        .lastName("Walter")
                        .build())
                .birthDate(LocalDate.now())
                .build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petServiceMap.findAll();
        assertEquals(1, pets.size());
        pets.add(new Pet());
        assertEquals(2, pets.size());

    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(id);
        assertEquals(0, petServiceMap.findAll().size());

    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(id));
        assertNull(petServiceMap.findById(id));
    }

    @Test
    void save() {
        petServiceMap.save(petServiceMap.findById(id));
        assertNotNull(petServiceMap.findById(id));
        assertNotNull(petServiceMap.findAll());
    }

    @Test
    void findById() {
        petServiceMap.save(petServiceMap.findById(id));
        assertNotNull(petServiceMap.findById(id));
    }
}