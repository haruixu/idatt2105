package idatt2105.oving5.backend.service;

import idatt2105.oving5.backend.model._Expression;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExpressionEvaluator {

    public static double evaluate(_Expression expr) {
        if (isDivideByZero(expr.getExpression())) {
            return Double.NaN;
        }
        Expression expression= new ExpressionBuilder(expr.getExpression()).build();
        return expression.evaluate();
    }

    private static boolean isDivideByZero(String equation) {
        return (equation.matches(".*/0.*"));
    }
}
