package com.example.freshermanement.controller;

import com.example.freshermanement.dto.request.AuthenticationRequest;
import com.example.freshermanement.dto.request.IntrospectRequest;
import com.example.freshermanement.dto.response.ApiResponse;
import com.example.freshermanement.dto.response.AuthenticationResponse;
import com.example.freshermanement.dto.response.IntrospectResponse;
import com.example.freshermanement.entity.User;
import com.example.freshermanement.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AccessLevel;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
