package com.example.learning_dev_2024.controller;

import com.example.learning_dev_2024.payload.request.EmailRQ;
import com.example.learning_dev_2024.payload.request.LoginRequest;
import com.example.learning_dev_2024.payload.request.UserRequest;
import com.example.learning_dev_2024.payload.response.APIResponse;
import com.example.learning_dev_2024.payload.response.ResponseFormat;
import com.example.learning_dev_2024.payload.response.ResponseService;
import com.example.learning_dev_2024.service.LoginService;
import com.example.learning_dev_2024.service.MailAuthenticationService;
import com.example.learning_dev_2024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {
    private final UserService userService;
    private final ResponseFormat format;
    private final LoginService loginService;
    private final MailAuthenticationService mailAuthenticationService;

    @Autowired
    public UserController(UserService userService,
                          ResponseFormat format,
                          LoginService loginService,
                          MailAuthenticationService mailAuthenticationService) {
        this.userService = userService;
        this.format = format;
        this.loginService = loginService;
        this.mailAuthenticationService = mailAuthenticationService;

    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<APIResponse> createUser(@RequestBody UserRequest user) {
        System.out.println(user.getIsTeacher());
        ResponseService rs = userService.createUser(user);
        if (rs.getCode() == 408) return format.badRequest("USER EXISTED");
        if (rs.getCode() == 200) return format.success(rs.getObject());
        if (rs.getCode() == 400) return format.badRequest("BAD INPUT");
        if (rs.getCode() == 404) return format.notFound("NOT FOUND");
        return format.confictRequest("CONFLICT");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<APIResponse> loginUser(@RequestBody LoginRequest userLogin) {
        ResponseService rs = loginService.loginUser(userLogin);
        if (rs.getCode() == 400 ) return format.badRequest("BAD INPUT");
        if (rs.getCode() == 401 ) return format.badRequest("BAD CREDENTIALS");
        if (rs.getCode() == 200 )return format.success(rs.getObject());
        if (rs.getCode() == 404 )return format.notFound("NOT FOUND");
        return format.confictRequest("CONFLICT");
    }

    @GetMapping(value = "/confirm-mail")
    public ResponseEntity<APIResponse> authenticationMail(@RequestParam String token) {
        ResponseService rs = mailAuthenticationService.confirmMail(token);
        if (rs.getCode() == 400) return format.badRequest("BAD INPUT");
        if (rs.getCode() == 404) return format.badRequest("TOKEN NOT FOUND");
        if (rs.getCode() == 200) return format.success(rs.getObject());
        return format.confictRequest("CONFLICT");
    }

}