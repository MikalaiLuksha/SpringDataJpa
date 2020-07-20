package tms.springdatajpa.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import tms.springdatajpa.entity.user.User;
import tms.springdatajpa.repository.UserRepository;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public boolean authUser(String password, String userName){
        User byUserName = userRepository.findByUserName(userName);
        if (byUserName!= null){
            return byUserName.getPassword().equals(password);
        }
        else return false;
    }

    public User getUser(String userName){
        return userRepository.findByUserName(userName);
    }
}
