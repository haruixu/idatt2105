package idatt2105.oving5.backend.service;

import idatt2105.oving5.backend.model.Equation;
import idatt2105.oving5.backend.model.User;
import idatt2105.oving5.backend.repository.UserRepository;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }
  public Optional<User> findUserById(long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findUserByUsername(String username) { return userRepository.findByUsername(username); }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public Equation saveUserWithEquation(User user, Equation equation) {
    user.addEquation(equation);
    user = userRepository.save(user);

    return user.getEquations().stream()
        .sorted(Comparator.comparing(Equation::getId).reversed())
        .filter(e -> e.equals(equation))
        .findFirst()
        .orElse(null);
  }
  public URI createUserUri(User user) {
   return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(user.getId())
        .toUri();
  }
}
