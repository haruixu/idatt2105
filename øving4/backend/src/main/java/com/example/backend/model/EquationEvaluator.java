package com.example.backend.model;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class EquationEvaluator {

  public static double evaluate(String equation) {
   if (isDivideByZero(equation)) {
     return Double.MAX_VALUE;
   }
    Expression expression = new ExpressionBuilder(equation).build();
    return expression.evaluate();
  }

  private static boolean isDivideByZero(String equation) {
    return (equation.matches(".*/0.*"));
  }
}
