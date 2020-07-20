package tms.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tms.springdatajpa.entity.user.User;

public interface UserRepository extends JpaRepository <User, Integer> {

    User findByUserName(String userName);
}
