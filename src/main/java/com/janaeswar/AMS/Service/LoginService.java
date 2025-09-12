package com.janaeswar.AMS.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public ResponseEntity<?> adminLogin(String userName, String password) {
         if(userName.equals("admin") && password.equals("admin123")){
             return ResponseEntity.ok("login successful-admin");
        }
        else if(userName.equals("superadmin") && password.equals("sadmin123")){
            return ResponseEntity.ok("login successful-superadmin");
        }
        return ResponseEntity.badRequest().body("invalid login");
    }
}
