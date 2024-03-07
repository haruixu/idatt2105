package idatt2105.oving5.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
public class User {

  @Id
  @Column(name="username")
  private String username;

  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    equations = new ArrayList<>();
  }
  public User() {

  }

  @OneToMany(mappedBy = "USERNAME", cascade = CascadeType.ALL)
  private List<Equation> equations;

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