package com.example.rug.services.map;

import com.example.rug.model.Pet;
import com.example.rug.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder()
                .name("Cat")
                .build());

    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeMapService.findAll();
        assertEquals(1, petTypes.size());
        assertNotNull(petTypeMapService.findAll());

    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(id);
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void delete() {
        petTypeMapService.delete(PetType.builder().name("Dog").build());
        System.out.println(petTypeMapService.findAll().size());
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void save() {
        assertEquals(1, petTypeMapService.findAll().size());
        assertNotNull(petTypeMapService.findAll());

        petTypeMapService.save(PetType
                .builder()
                .name("Dog")
                .build());

        assertEquals(2, petTypeMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals("Cat", petTypeMapService.findById(id).getName());
        assertNotNull(petTypeMapService.findById(id));

    }
}