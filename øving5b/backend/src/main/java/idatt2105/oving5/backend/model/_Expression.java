package idatt2105.oving5.backend.model;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class _Expression {
     private final String expression;

     public _Expression(@NonNull String expression) {
         this.expression = expression;
     }
 }