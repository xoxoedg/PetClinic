package com.example.rug.repositories;

import com.example.rug.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.events.Event;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String Name);
}
