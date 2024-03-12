package idatt2105.oving5.backend.controller;

import idatt2105.oving5.backend.model.User;
import idatt2105.oving5.backend.service.UserService;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

  @Autowired
  private UserService userService;

  private static final Logger logger = Logger.getLogger(UserController.class.getName());

  @PostMapping("/users")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    logger.info("Received user: " + user);
    if (user == null) {
      logger.severe("User cannot be null");
    }
    user = userService.saveUser(user);

    return ResponseEntity.created(userService.createUserUri(user)).body(user);
  }

  @GetMapping("/users")
  public ResponseEntity<?> getUsers() {
    try {
      return ResponseEntity.ok().body(userService.findAllUsers());
    } catch (Exception e) {
      logger.severe("you are stupid ");
      return ResponseEntity.badRequest().build();
    }
  }
}
