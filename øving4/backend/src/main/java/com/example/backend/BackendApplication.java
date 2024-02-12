package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  //endepunktet skal være et calculate endepunkt

  /*
  Lag et nytt Maven-prosjekt med Spring Boot. I denne oppgaven skal du opprette ett eller
  flere endepunkt for kalkulatoren. Endepunkt(er) skal ta imot et regnestykke, og gi et svar i
  form av JSON.

  Alt av requests skal logges i konsollen på backend. Husk på lagdeling.
  Krav til godkjent:
      - Fungerende endepunkt(er) for kalkulasjon
      - Lagdeling (controller, model, service og evt annet)
      - Logging
*/
}
