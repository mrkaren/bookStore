package am.itspace.bookstore.service;

import am.itspace.bookstore.entity.User;
import am.itspace.bookstore.exception.DuplicateResourceException;
import am.itspace.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) throws DuplicateResourceException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User already exists!");
        }
        userRepository.save(user);

    }

}
