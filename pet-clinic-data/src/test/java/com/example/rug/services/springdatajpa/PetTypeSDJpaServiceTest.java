package com.example.rug.services.springdatajpa;

import com.example.rug.model.PetType;
import com.example.rug.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSDJpaServiceTest {
    private final Long id = 1L;
    private static final String NAME1 = "Cat";
    private static final String NAME2 = "Dog";
    PetType returnPetType;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService petTypeSDJpaService;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().name(NAME1).build();
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = Set.of(PetType.builder().name(NAME2).build(), returnPetType);
        when(petTypeRepository.findAll()).thenReturn(petTypeSet);
        Set<PetType> returnedPetTypes = petTypeSDJpaService.findAll();

        assertEquals(2, returnedPetTypes.size());
        assertNotNull(returnedPetTypes);
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));
        PetType petType = petTypeSDJpaService.findById(id);
        assertNotNull(petType);

    }

    @Test
    void save() {
        PetType petType = PetType.builder().name(NAME1).build();
        when(petTypeRepository.save(any())).thenReturn(returnPetType);
        PetType savedPetType = petTypeSDJpaService.save(petType);

        assertNotNull(savedPetType);

    }

    @Test
    void delete() {
        petTypeSDJpaService.delete(returnPetType);
        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        petTypeSDJpaService.deleteById(id);
        verify(petTypeRepository).deleteById(anyLong());
    }
}