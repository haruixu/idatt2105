package idatt2105.oving5.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// fixme - rename table
@Entity(name = "Equation")
public class Equation {

 @Id
 @GeneratedValue
 private Long id;

 private String expression;

 private double answer;

 public Equation(String expression, double answer) {
   this.expression = expression;
   this.answer = answer;
 }

 public Equation() {

 }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExpression() {
    return expression;
  }

  public void setExpression(String lhs) {
    this.expression = lhs;
  }

  public double getAnswer() {
    return answer;
  }

  public void setAnswer(double answer) {
    this.answer = answer;
  }
}
