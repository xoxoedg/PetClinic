package com.example.rug.services.springdatajpa;

import com.example.rug.model.Owner;
import com.example.rug.repositories.OwnerRepository;
import com.example.rug.repositories.PetRepository;
import com.example.rug.repositories.PetTypeRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

     @Mock
     OwnerRepository ownerRepository;

     @Mock
     PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    private final String smith = "Smith";

    Owner returnOwner;


    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(this.smith).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());

    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = ownerSDJpaService.findById(1L);

        assertNotNull(owner);

    }

    @Test
    void save() {

        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = ownerSDJpaService.save(ownerToSave);

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = ownerSDJpaService.findByLastName(this.smith);


        assertEquals(this.smith, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }
}