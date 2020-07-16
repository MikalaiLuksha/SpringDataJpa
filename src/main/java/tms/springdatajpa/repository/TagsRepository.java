package tms.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tms.springdatajpa.entity.pet.Tags;

public interface TagsRepository extends JpaRepository<Tags, Integer> {
}
