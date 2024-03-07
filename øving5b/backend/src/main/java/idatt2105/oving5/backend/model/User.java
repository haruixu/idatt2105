package idatt2105.oving5.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

  @Id
  @Column(name="USERNAME")
  private String username;

  private String password;

  @OneToMany(mappedBy = "USERNAME", cascade = CascadeType.ALL)
  private List<Equation> equations = new ArrayList<>();

  public void addEquation(Equation equation) {
    equations.add(equation);
  }

  public List<Equation> getEquations() {
    return equations;
  }

  public void setEquations(List<Equation> equations) {
    this.equations = equations;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}