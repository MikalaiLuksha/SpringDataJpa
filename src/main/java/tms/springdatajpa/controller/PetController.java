package tms.springdatajpa.controller;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tms.springdatajpa.entity.ApiResponse;
import tms.springdatajpa.entity.pet.Pet;
import tms.springdatajpa.entity.pet.Status;
import tms.springdatajpa.service.PetService;

import java.util.List;

@Data
@RestController
@RequestMapping(path = "/pet")
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<ApiResponse> createPet(@RequestBody Pet pet) {
        petService.addPet(pet);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "pet", "successful operation"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateExistingPet(@RequestBody Pet pet) {
        int id = 1;
        petService.updatePet(pet, id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "pet", "successful operation"), HttpStatus.CREATED);
    }

    @GetMapping(path = "/findByStatus")
    public ResponseEntity<List<Pet>> findByStatus(Status status) {
        List<Pet> pets = petService.returnPerByStatus(status);
        return new ResponseEntity<>(pets, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pet> findPet(@PathVariable(name = "id") int id) {
        Pet pet = petService.returnPet(id);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> updatePetInDB(@PathVariable(name = "id") int id, @RequestBody Pet pet) {
        String name = pet.getName();
        Status status = pet.getStatus();
        petService.updateNameAndStatusById(id, name, status);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "pet", "successful operation"), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deletedPet(@PathVariable(name = "id") int id) {
        petService.deletedPetById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "pet", "successful operation"), HttpStatus.CREATED);
    }


}
