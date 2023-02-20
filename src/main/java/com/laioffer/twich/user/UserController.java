package com.laioffer.twich.user;

import com.laioffer.twich.model.RegisterBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.OK)
    void register(@RequestBody() RegisterBody body) {
        userService.register(body.username(), body.password()
                , body.firstName(), body.lastName());
    }
}
