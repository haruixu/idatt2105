package idatt2105.oving5.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @PostMapping("/token")
    public ResponseEntity<?> generateToken() {
        return ResponseEntity.ok().build();
    }

    // Token is generated for each login
    // Token is valid for a certain amount of time, cached in browser (if using session storage)
    // After time limit exceeded, token becomes invalidated - need to log in and generate new token
    // If we open up a new tab, you likely need to log in again, same goes for closing tab and reopening
}
