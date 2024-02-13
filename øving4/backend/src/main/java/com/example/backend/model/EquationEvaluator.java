package com.example.backend.model;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class EquationEvaluator {

  public static double evaluate(String equation) {
   if (isDivideByZero(equation)) {
     return Double.MAX_VALUE;
   }

   if (hasDoubleOperator(equation)) {
     return Double.MIN_VALUE;
   }

    Expression expression = new ExpressionBuilder(equation).build();
    return expression.evaluate();
  }

  private static boolean isDivideByZero(String equation) {
    return (equation.matches(".*/0.*"));
  }

  private static boolean hasDoubleOperator(String equation) {
    return (equation.matches(".*[+\\-*/]{2}.*"));
  }
}
