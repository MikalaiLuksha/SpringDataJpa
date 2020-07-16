package tms.springdatajpa.service;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Service;
import tms.springdatajpa.entity.pet.Pet;
import tms.springdatajpa.entity.pet.Status;
import tms.springdatajpa.repository.CategoryRepository;
import tms.springdatajpa.repository.PetRepository;
import tms.springdatajpa.repository.TagsRepository;

import java.util.List;

@Data
@Service
public class PetService {

    private final CategoryRepository categoryRepository;
    private final PetRepository petRepository;
    private final TagsRepository tagsRepository;

    public void addPet(Pet pet){
        categoryRepository.save(pet.getCategory());
        tagsRepository.saveAll(pet.getTags());
        petRepository.save(pet);
    }

    public List<Pet> returnPerByStatus(Status status) {
        return petRepository.findAllByStatus(status);
    }

    public Pet returnPet(int id) {
        return petRepository.findById(id);
    }

    public void updateNameAndStatusById(int id, String name, Status status) {
        petRepository.updateNameAndStatus(name, status, id);
    }

    public void deletedPetById(int id) {
        petRepository.deleteById(id);
    }

    public void updatePet(Pet pet, int id) {
//          petRepository.updatePet(pet, id);
        Pet byId = petRepository.findById(id);
        byId.setName(pet.getName());
        byId.setCategory(pet.getCategory());
        byId.setStatus(pet.getStatus());
        byId.setTags(pet.getTags());
        byId.setId(pet.getId());
        categoryRepository.save(byId.getCategory());
        tagsRepository.saveAll(byId.getTags());
        petRepository.save(byId);
    }
}

