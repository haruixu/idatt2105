package idatt2105.oving5.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// fixme - rename table
@Entity
@Table(name = "REQUATIONS")
public class Equation {

 @Id
 @GeneratedValue
 private Long id;

 private String lhs;

 private double answer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLhs() {
    return lhs;
  }

  public void setLhs(String lhs) {
    this.lhs = lhs;
  }

  public double getAnswer() {
    return answer;
  }

  public void setAnswer(double answer) {
    this.answer = answer;
  }
}
