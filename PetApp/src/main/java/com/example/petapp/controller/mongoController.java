package com.example.petapp.controller;

import com.example.petapp.domain.Owner;
import com.example.petapp.domain.Pet;
import com.example.petapp.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class mongoController {
    private final MongoService service;


    @Autowired
    public mongoController(MongoService service) {
        this.service = service;
    }

    @PostMapping("/owner")
    public void saveOrUpdateOwner(@RequestBody Owner owner) {
        service.saveOrUpdateOwner(owner);
    }

    @GetMapping("/owners")
    public List<Owner> findAllOwner() {
        return service.findAllOwner();
    }

    @GetMapping("/owner/{id}")
    public Owner findOwnerById(@PathVariable String id) {
        return service.findOwnerById(id);
    }

    @PutMapping("/owner/{id}/pet")
    public void saveOrUpdatePet(@PathVariable String id,@RequestBody Pet pet) {
        Owner owner = service.findOwnerById(id);
        owner.getPets().add(pet);
        service.saveOrUpdateOwner(owner);
    }

    @DeleteMapping("/owner/{id}")
    public void deleteOwnerById(@PathVariable String id) {
        service.deleteOwnerById(id);
    }





}
