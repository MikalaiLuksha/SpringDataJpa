package tms.springdatajpa.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tms.springdatajpa.entity.pet.Pet;
import tms.springdatajpa.entity.pet.Status;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {


    List<Pet> findAllByStatus (Status status);
    Pet findById (int id);
    Pet deleteById (int id);


    @Transactional
    @Modifying()
    @Query("update Pet c set c.name=:name, c.status =:status where c.id=:id")
    void updateNameAndStatus(@Param("name") String name, @Param("status") Status status, @Param("id") int id);


}
