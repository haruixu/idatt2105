package idatt2105.oving5.backend.controller;

import idatt2105.oving5.backend.model.Equation;
import idatt2105.oving5.backend.model.User;
import idatt2105.oving5.backend.model.Expr;
import idatt2105.oving5.backend.service.EquationService;
import idatt2105.oving5.backend.service.ExpressionService;
import idatt2105.oving5.backend.service.UserService;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquationController {

  private final UserService userService;

  private final EquationService equationService;

  private final ExpressionService expressionService;

  public static final Logger logger = Logger.getLogger(EquationController.class.getName());

  public EquationController(UserService userService, EquationService equationService,
      ExpressionService expressionService) {
    this.userService = userService;
    this.equationService = equationService;
    this.expressionService = expressionService;
  }

  //@GetMapping("/calculations")
  //public ResponseEntity<?> getEquations(request) {
  //  logger.info("Received equation request for user: " + id);
  //  Optional<User> user = userService.findUserByUsername(username);

  //  // todo paginate response
  //  if (user.isPresent()) {
  //    return ResponseEntity.ok(user.get().getEquations());
  //  } else {
  //    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //  }
  //}

  @PostMapping("/calculate")
  public ResponseEntity<?> calculate(
      @RequestBody @NonNull Expr expression,
      @RequestHeader("Authorization") String token,
      @RequestHeader("Content-length") long length) {

    logger.info("length is: " + length);
    logger.info("token is: " + token);
    logger.info("Received post request for: " + expression);

    double answer = expressionService.evaluate(expression);

    if (Double.isNaN(answer)) {
      return ResponseEntity.badRequest().body("Cannot divide by zero");
    }

    Equation equation = new Equation(expression, answer);

    Optional<User> user = userService.findUserByUsername("");

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
