package idatt2105.oving5.backend.controller;

import idatt2105.oving5.backend.dto.AuthenticationRequest;
import idatt2105.oving5.backend.dto.RegisterRequest;
import idatt2105.oving5.backend.expections.UsernameAlreadyTakenException;
import idatt2105.oving5.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class AuthController {

    // TODO: Token is generated for each login
    // Token is valid for a certain amount of time, cached in browser (if using session storage)
    // After time limit exceeded, token becomes invalidated - need to log in and generate new token
    // If we open up a new tab, you likely need to log in again, same goes for closing tab and reopening
    private final AuthenticationService service;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        logger.info("Received signup: " + request);
        // TODO: Created 201 with body!!
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (UsernameAlreadyTakenException usernameException) {
            logger.severe("Duplicate username signup");
            return ResponseEntity.badRequest().body(usernameException.getMessage());
        } catch (Exception e) {
            logger.severe("Failed to signup due to: " + e.getClass());
            return ResponseEntity.internalServerError().body("Could not sign up");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> register(
            @RequestBody AuthenticationRequest request
    ) {
        logger.info("Received login: " + request);
        return ResponseEntity.ok(service.authenticate(request));
    }
}
