package com.janaeswar.AMS.Controller.OTP;

import com.janaeswar.AMS.Service.OTP.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> requestOtp(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(otpService.generateAndSendOtp("+91"+phoneNumber));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        if (otpService.validateOtp("+91"+phoneNumber, otp)) {
            return ResponseEntity.ok("OTP verified successfully. Login granted.");
        } else {
            return ResponseEntity.status(401).body("Invalid or expired OTP");
        }
    }

}
