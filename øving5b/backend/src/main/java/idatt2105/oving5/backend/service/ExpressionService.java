package idatt2105.oving5.backend.service;

import idatt2105.oving5.backend.model.Expr;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

@Service
public class ExpressionService {

    public double evaluate(Expr expr) {
        if (isDivideByZero(expr.getExpression())) {
            return Double.NaN;
        }
        Expression expression= new ExpressionBuilder(expr.getExpression()).build();
        return expression.evaluate();
    }

    private boolean isDivideByZero(String equation) {
        return (equation.matches(".*/0.*"));
    }
}
