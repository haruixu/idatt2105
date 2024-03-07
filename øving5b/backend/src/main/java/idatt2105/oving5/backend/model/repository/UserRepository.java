package idatt2105.oving5.backend.model.repository;

import idatt2105.oving5.backend.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findUserByUsername(String username);
}
