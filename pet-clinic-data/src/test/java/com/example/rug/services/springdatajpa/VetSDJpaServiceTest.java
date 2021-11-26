package com.example.rug.services.springdatajpa;

import com.example.rug.model.Speciality;
import com.example.rug.model.Vet;
import com.example.rug.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {
    public static final Long id = 1L;
    public static final String TEST = "Test";
    Vet returnVet;

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService vetSDJpaService;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().specialities(Set.of(Speciality.builder().description(TEST).build())).build();
    }

    @Test
    void findAll() {
        Set<Vet> vets = Set.of(returnVet);
        when(vetRepository.findAll()).thenReturn(vets);
        Set<Vet> returnVets = vetSDJpaService.findAll();

        assertEquals(1, returnVets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));
        Vet vet = vetSDJpaService.findById(id);
        assertNotNull(vet);
    }

    @Test
    void save() {
        Vet vet = Vet
                .builder()
                .specialities(Set.of(Speciality
                        .builder()
                        .description(TEST)
                        .build()))
                .build();

        when(vetRepository.save(any())).thenReturn(vet);
        Vet returnedVet = vetSDJpaService.save(returnVet);
        assertNotNull(returnedVet);
    }

    @Test
    void delete() {
        vetSDJpaService.delete(returnVet);
        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        vetSDJpaService.deleteById(id);
        verify(vetRepository).deleteById(anyLong());
    }
}