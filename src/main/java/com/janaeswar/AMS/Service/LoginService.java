package com.janaeswar.AMS.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public ResponseEntity<?> adminLogin(String userName, String password) {
         if(userName.equals("admin") && password.equals("admin123")){
             return ResponseEntity.ok("login successful");
        }
        return ResponseEntity.badRequest().body("invalid login");
    }
}
