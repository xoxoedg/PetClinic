package com.example.rug.services.springdatajpa;

import com.example.rug.model.Speciality;
import com.example.rug.repositories.SpecialtyRepository;
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
class SpecialtySDJpaServiceTest {
    private final Long id = 1L;
    private static final String DESCRIPTION = "Test";
    Speciality returnSpecialty;

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialtySDJpaService specialtySDJpaService;

    @BeforeEach
    void setUp() {
        returnSpecialty = Speciality.builder().description(DESCRIPTION).build();

    }

    @Test
    void findAll() {
        Set<Speciality> specialitySet = Set.of(Speciality.builder().description("Hello").build(), returnSpecialty);
        when(specialtyRepository.findAll()).thenReturn(specialitySet);
        Set<Speciality> returnSpecialtySet = specialtySDJpaService.findAll();
        assertEquals(2, returnSpecialtySet.size());
        returnSpecialtySet.add(Speciality.builder().description("Hello2").build());
        assertEquals(3, returnSpecialtySet.size());

    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpecialty));
        Speciality speciality = specialtySDJpaService.findById(id);
        assertNotNull(speciality);
    }

    @Test
    void save() {
        Speciality speciality = Speciality.builder().description(DESCRIPTION).build();
        when(specialtyRepository.save(any())).thenReturn(speciality);
        Speciality returnSpecialty = specialtySDJpaService.save(speciality);
        assertNotNull(returnSpecialty);

    }

    @Test
    void delete() {
        specialtySDJpaService.delete(returnSpecialty);
        verify(specialtyRepository).delete(any());
    }

    @Test
    void deleteById() {
        specialtySDJpaService.deleteById(1L);
        verify(specialtyRepository).deleteById(anyLong());
    }
}