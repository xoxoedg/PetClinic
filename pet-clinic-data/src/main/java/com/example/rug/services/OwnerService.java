package com.example.rug.services;

import com.example.rug.model.Owner;

import java.util.Set;

public interface OwnerService extends  CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
