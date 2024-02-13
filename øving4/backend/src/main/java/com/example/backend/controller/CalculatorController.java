package com.example.backend.controller;

import com.example.backend.model.Calculation;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

  private final List<Calculation> calculations = new ArrayList<>();

  private static final Logger logger = Logger.getLogger(CalculatorController.class.getName());

  //todo get logger into this shit
  /**
   * Takes in an input equation and returns the answer - query param for now is required
   * @param equation yes
   * @return yes
   */
  @CrossOrigin
  @GetMapping("/calculate")
  public ResponseEntity<HashMap<String, Object>> calculate(
      @RequestParam(value="eq") String equation) {

    HashMap<String, Object> hashMap = new HashMap<>();
    try {
      Calculation calculation = new Calculation(equation);

      if (calculation.getAnswer() != Double.MAX_VALUE && calculation.getAnswer() != Double.MIN_VALUE ) {
        //Shit id-giving, men jaja
        calculation.setId(calculations.size());
        calculations.add(calculation);

        logger.info(calculation.getEquation() + "=" + calculation.getAnswer());
        hashMap.put("status", 0);
        hashMap.put("calculation", calculation);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
      }

      logger.info("Invalid equation: " + equation);
      hashMap.put("status", 1);
      if (calculation.getAnswer() == Double.MAX_VALUE) {
        hashMap.put("context", "Cannot divide by zero");
      } else {
        hashMap.put("context", "Cannot use two operators after each other");
      }
      return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);

    } catch(Exception e) {
      System.out.println(e);
      logger.info("Invalid equation: " + equation);
      hashMap.put("status", 1);
      hashMap.put("context", "Invalid equation");
      return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
    }
  }
}
