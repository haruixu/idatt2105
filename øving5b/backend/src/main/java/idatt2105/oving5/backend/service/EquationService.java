package idatt2105.oving5.backend.service;

import idatt2105.oving5.backend.model.Equation;
import idatt2105.oving5.backend.repository.EquationRepository;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class EquationService {

  private EquationRepository equationRepository;

  @Autowired
  public EquationService(EquationRepository equationRepository) {
    this.equationRepository = equationRepository;
  }

  public URI createEquationUri(Equation equation) {
    return ServletUriComponentsBuilder
        .fromPath("/equations")
        .path("/{id}")
        .buildAndExpand(equation.getId())
        .toUri();
  }


}
