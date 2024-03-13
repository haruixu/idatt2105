package idatt2105.oving5.backend.controller;

import idatt2105.oving5.backend.dto.AuthenticationRequest;
import idatt2105.oving5.backend.dto.RegisterRequest;
import idatt2105.oving5.backend.expections.UsernameAlreadyTakenException;
import idatt2105.oving5.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    // TODO: Token is generated for each login
    // Token is valid for a certain amount of time, cached in browser (if using session storage)
    // After time limit exceeded, token becomes invalidated - need to log in and generate new token
    // If we open up a new tab, you likely need to log in again, same goes for closing tab and reopening
    // TODO: Unsure how home authentication becomes, as we have no backend endpoint
    private final AuthenticationService service;

    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) throws UsernameAlreadyTakenException {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
