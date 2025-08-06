package crps.example.certificate.service;

import crps.example.certificate.model.Role;
import crps.example.certificate.model.User;
import crps.example.certificate.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        if (user.getRole() == null) {
            user.setRole(Role.STUDENT);
        }
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User updateUser(Long id, User updatedUser) {
        User existing = getUserById(id);
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        existing.setRole(updatedUser.getRole());
        return userRepo.save(existing);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
