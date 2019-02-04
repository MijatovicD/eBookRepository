package uns.ac.rs.eBookRepository.eBookRepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uns.ac.rs.eBookRepository.eBookRepository.entity.User;
import uns.ac.rs.eBookRepository.eBookRepository.model.UserTokenState;
import uns.ac.rs.eBookRepository.eBookRepository.token.JwtAuthenticationRequest;
import uns.ac.rs.eBookRepository.eBookRepository.token.TokenHelper;
import uns.ac.rs.eBookRepository.eBookRepository.service.CustomUserDetailsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) throws AuthenticationException, IOException {

        // Izvrsavanje security dela
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Ubaci username + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token
        User user = (User)authentication.getPrincipal();
        String jws = tokenHelper.generateToken(user.getUsername());

        // Vrati token kao odgovor na uspesno autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jws));
    }

//    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
//    public ResponseEntity<?> refreshAuthenticationToken(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Principal principal
//            ) {
//
//        String authToken = tokenHelper.getToken( request );
//
//        Device device = deviceProvider.getCurrentDevice(request);
//
//        if (authToken != null && principal != null) {
//
//            // TODO check user password last update
//            String refreshedToken = tokenHelper.refreshToken(authToken, device);
//            int expiresIn = tokenHelper.getExpiredIn(device);
//
//            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
//        } else {
//            UserTokenState userTokenState = new UserTokenState();
//            return ResponseEntity.accepted().body(userTokenState);
//        }
//    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
   // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put( "result", "success" );
        return ResponseEntity.accepted().body(result);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
}
