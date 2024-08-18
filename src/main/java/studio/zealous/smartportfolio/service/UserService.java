package studio.zealous.smartportfolio.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.entity.User;
import studio.zealous.smartportfolio.repository.jpa.UserRepository;

import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByAngelUserId(String angelUserId) {
        Optional<User> userOptional = userRepository.findByAngelUserId(angelUserId);
        return userOptional.orElse(null); // Handle null case as needed
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null); // Handle null case as needed
    }

    public Long getIdFromAngelUserId(String angelUserId){
        Long id = userRepository.getIdByAngelUserIdNative(angelUserId);
        return id;
    }

    public String saveUser(User user) {
        return userRepository.save(user).getAngelUserId();
    }

}
