package com.Cybersoft.uniclub09.controller;

import com.Cybersoft.uniclub09.payload.request.SignupRequest;
import com.Cybersoft.uniclub09.payload.respone.BaseRespone;
import com.Cybersoft.uniclub09.services.AuthenticationServices;
import com.Cybersoft.uniclub09.util.JWTHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationServices authenticationServices;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTHelper jwtHelper;

    /*
    * Base Response:
    * {
    *   "code": 200,
    *   "message": "Login successful",
    *  "data": {
    *
    *   }
    * }
    * */
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String email,
                                         @RequestParam String password) {
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        String data = jwtHelper.decodeToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIZWxsbyBKV1QgQkMwOSJ9.YrCsOZNLBbIOsQHVGUDDqIf-hwLsftDgAstrMTWdBgY");
//        System.out.println("Decoded Data: " + data);
//      boolean isSuccessful = false;
        String token = authenticationServices.checkLogin(email, password);
//        return ResponseEntity.ok(secretString);
        boolean isSuccessful = token != null && !token.isEmpty();
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(isSuccessful ? 200 : 400);
        baseRespone.setMessage(isSuccessful ? "Login successful" : "Login failed");
        baseRespone.setData(isSuccessful ? token : null);
        return ResponseEntity.ok(baseRespone);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signupRequest){
        // Request body là truyền tham số theo định dạng JSON
        authenticationServices.signup(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getFullName());
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("Sign up successful" + signupRequest.getEmail());
        baseRespone.setData(null);

        return ResponseEntity.ok(baseRespone);
    }
}
