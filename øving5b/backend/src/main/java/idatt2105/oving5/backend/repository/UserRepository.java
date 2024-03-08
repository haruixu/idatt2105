package idatt2105.oving5.backend.repository;

import idatt2105.oving5.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
