package App.controllers.auth;

import App.controllers.enums.AuthResponse;
import App.entity.User;
import App.jwt.TokenProvider;
import App.logic.PasswordHelper;
import App.models.auth.AuthorisationModel;
import App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final PasswordHelper passwordHelper;

    @Autowired
    public LoginController(TokenProvider tokenProvider, UserService userService, PasswordHelper passwordHelper) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.passwordHelper = passwordHelper;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthorisationModel authModel) {
        User user = userService.findByEmail(authModel.getEmail())
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AuthResponse.WRONG_CREDENTIALS.toString()); });

        if(!passwordHelper.isMatch(authModel.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AuthResponse.WRONG_CREDENTIALS.toString());
        }

        try {
            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("token", tokenProvider.createToken(user.getId(), user.getFirstName(), user.getLastName()));
            model.put("user", user);
            return ok(model);
        } catch(AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AuthResponse.UNEXPECTED_ERROR.toString());
        }
    }
}
