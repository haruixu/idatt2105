package idatt2105.oving5.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Equations")
public class Equation {

    @Id
    @GeneratedValue
    private Long id;

    private String expression;

    private double answer;

    public Equation(Expr expression, double answer) {
        this.expression = expression.getExpression();
        this.answer = answer;
    }
}
