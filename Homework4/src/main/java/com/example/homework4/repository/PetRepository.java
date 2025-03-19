package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PetRepository {
    private final Map<Long, Pet> pets = new HashMap<>();
    private Long nextId = 1L;

    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(nextId++);
        }
        pets.put(pet.getId(), pet);
        return pet;
    }

    public Optional<Pet> findById(Long id) {
        return Optional.ofNullable(pets.get(id));
    }

    public void deleteById(Long id) {
        pets.remove(id);
    }
}