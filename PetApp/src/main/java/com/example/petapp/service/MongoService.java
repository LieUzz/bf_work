package com.example.petapp.service;

import com.example.petapp.domain.Owner;
import com.example.petapp.repository.MongoDemoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {
    private final MongoDemoRepo repository;


    public MongoService(MongoDemoRepo repository) {
        this.repository = repository;
    }

    public List<Owner> findAllOwner() {
        return repository.findAll();
    }

    public Owner findOwnerById(String id) {
        return repository.findById(id).orElse(new Owner());
    }

    public void saveOrUpdateOwner(Owner owner) {
        repository.save(owner);
    }

    public void deleteOwnerById(String id) {
        repository.deleteById(id);
    }
}
