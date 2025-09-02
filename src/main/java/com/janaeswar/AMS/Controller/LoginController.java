package com.janaeswar.AMS.Controller;
import com.janaeswar.AMS.Service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {

    public final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/admin-login")
    public ResponseEntity<?> register(@RequestParam String userName, @RequestParam String password){
        return  loginService.adminLogin(userName,password);
    }
}
