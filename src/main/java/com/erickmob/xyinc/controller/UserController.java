package com.erickmob.xyinc.controller;


import com.erickmob.xyinc.dto.PasswordChangeDTO;
import com.erickmob.xyinc.entity.User;
import com.erickmob.xyinc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/user")
@Api(tags = "User")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    @ApiOperation(value = "Saving new user.")
    public ResponseEntity<User> save(@Valid @RequestBody User user) throws IOException {
        log.debug("REST request to convert: {}", user);
        userService.save(user);
        return ok(user);
    }

    @PostMapping(path = "/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    @GetMapping(path = "/init")
    public String init() {
        return "ok";
    }

    @GetMapping("/currentUser")
    public ResponseEntity currentUser(@AuthenticationPrincipal User userDetails) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }
}
