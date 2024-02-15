package com.example.backend.model;

import com.example.backend.service.EquationEvaluator;

public class Calculation {

  public int id;
  private double answer;
  private String equation;

  public Calculation(String equation) {
    this.equation = equation;
    answer = calculate(equation);
  }
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEquation() {
    return equation;
  }

  public void setEquation(String equation) {
    this.equation = equation;
  }

  public double getAnswer() {
    return answer;
  }

  public void setAnswer(int answer) {
    this.answer = answer;
  }

  public double calculate(String equation) {
    return EquationEvaluator.evaluate(equation);
  }
}
