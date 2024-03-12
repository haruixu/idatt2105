package idatt2105.oving5.backend.controller;

import idatt2105.oving5.backend.model.Equation;
import idatt2105.oving5.backend.model.User;
import idatt2105.oving5.backend.service.EquationService;
import idatt2105.oving5.backend.service.UserService;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquationController {

  private final UserService userService;

  private final EquationService equationService;
  public static final Logger logger = Logger.getLogger(EquationController.class.getName());

  public EquationController(UserService userService, EquationService equationService) {
    this.userService = userService;
    this.equationService = equationService;
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<?> getEquationsForUser(@PathVariable("id") Long id) {
    logger.info("Received equation request for user: " + id);
    Optional<User> user = userService.findUserById(id);

    // todo paginate response
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get().getEquations());
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/calculate")
  public ResponseEntity<?> calculate(@RequestBody @NonNull Equation equation, @RequestParam() Long user_id) {
    logger.info("Received post request for: " + equation);

      Optional<User> user = userService.findUserById(user_id);

    if (user.isPresent()) {
      User _user = user.get();
      Equation savedEquation = userService.saveUserWithEquation(_user, equation);

      return ResponseEntity.created(equationService.createEquationUri(savedEquation)).body(savedEquation);
    } else {
      logger.severe("User does not exist");
      return ResponseEntity.notFound().build();
    }
  }
}
