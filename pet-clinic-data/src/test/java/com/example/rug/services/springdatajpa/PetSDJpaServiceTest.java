package com.example.rug.services.springdatajpa;

import com.example.rug.model.Pet;
import com.example.rug.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService petSDJpaService;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet
                .builder()
                .name("Wuff")
                .build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPets = new HashSet<>();
        returnPets.add(Pet.builder().name("Caty").build());
        returnPets.add(Pet.builder().name("Tomy").build());

        when(petRepository.findAll()).thenReturn(returnPets);

        Set<Pet> pets = petSDJpaService.findAll();
        assertEquals(2, pets.size());
        assertNotNull(pets);

    }

    @Test
    void findById() {
        Long id = 1L;
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));
        Pet pet = petSDJpaService.findById(id);
        assertNotNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().name("lol").build();
        when(petRepository.save(any())).thenReturn(returnPet);
        Pet savedPet = petRepository.save(petToSave);

        assertNotNull(savedPet);
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {

    }
}