package tms.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tms.springdatajpa.entity.pet.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
