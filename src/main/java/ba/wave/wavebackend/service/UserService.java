package ba.wave.wavebackend.service;

import ba.wave.wavebackend.model.user.User;
import ba.wave.wavebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.existsUsersByEmail(email);
    }

}
