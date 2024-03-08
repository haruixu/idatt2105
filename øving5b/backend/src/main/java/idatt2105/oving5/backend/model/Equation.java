package idatt2105.oving5.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

// fixme - rename table
@Entity(name = "Equations")
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Equation equation)) {
      return false;
    }
    return Double.compare(equation.getAnswer(), getAnswer()) == 0 && getExpression().equals(
        equation.getExpression());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getExpression(), getAnswer());
  }

  @Override
  public String toString() {
    return "Equation{" +
        "id=" + id +
        ", expression='" + expression + '\'' +
        ", answer=" + answer +
        '}';
  }
}
