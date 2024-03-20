package idatt2105.oving5.backend.service;

import idatt2105.oving5.backend.model.Equation;
import idatt2105.oving5.backend.model.User;
import idatt2105.oving5.backend.repository.UserRepository;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

  public List<Equation> findAllEquations(String username, int page, int size) {
    Optional<User> user = findUserByUsername(username);

    if (user.isPresent()) {
      System.out.println(user.get().getEquations());
      List<Equation> equations = user.get().getEquations();

      if (equations.size() < 10) {
        return equations.reversed().subList(0, equations.size() - 1);
      }
      else {
        return equations.reversed().subList(0, 10);
      }
    }
    else {
      throw new IllegalArgumentException("Username does not exist");
    }
  }

  private Pageable createPageRequestUsing(int page, int size) {
    // Sorting after id - highest id = latest entry
    Sort sort = Sort.by("id").descending();
    return PageRequest.of(page, size, sort);
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
