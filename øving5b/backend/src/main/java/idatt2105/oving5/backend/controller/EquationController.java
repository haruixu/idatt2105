package idatt2105.oving5.backend.controller;

import idatt2105.oving5.backend.model.Equation;
import idatt2105.oving5.backend.model.User;
import idatt2105.oving5.backend.model.Expr;
import idatt2105.oving5.backend.service.ExpressionService;
import idatt2105.oving5.backend.service.JwtService;
import idatt2105.oving5.backend.service.UserService;

import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EquationController {

  private final UserService userService;

  private final ExpressionService expressionService;

  private final JwtService jwtService;

  public static final Logger logger = Logger.getLogger(EquationController.class.getName());

  public EquationController(UserService userService,
      ExpressionService expressionService, JwtService jwtService) {
    this.userService = userService;
    this.expressionService = expressionService;
    this.jwtService = jwtService;
  }

  @GetMapping("/calculations")
  public ResponseEntity<?> getEquations(
          @RequestHeader("Authorization") String token) {

    try {
      String username = jwtService.extractUsername(token.substring(7));

      return ResponseEntity.ok(userService.findAllEquations(username, 0, 10));

    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/calculate")
  public ResponseEntity<?> calculate(
      @RequestBody @NonNull Expr expression,
      @RequestHeader("Authorization") String token) {

    try {
      double answer = expressionService.evaluate(expression);
      if (Double.isNaN(answer)) {
        return ResponseEntity.badRequest().body("Cannot divide by zero");
      }
      Equation equation = new Equation(expression, answer);
      logger.info("New equation: " + equation);

      Optional<User> user = userService.findUserByUsername(jwtService.extractUsername(token.substring(7)));
      if (user.isPresent()) {
        User _user = user.get();
        _user.addEquation(equation);
        userService.saveUser(_user);
        return ResponseEntity.ok().body(answer);
      } else {
        logger.severe("User does not exist");
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      logger.severe(e.getMessage());
      return ResponseEntity.badRequest().body("Invalid token");
    }
  }
}
