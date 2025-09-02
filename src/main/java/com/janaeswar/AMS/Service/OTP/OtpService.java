package com.janaeswar.AMS.Service.OTP;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    // Store OTPs in-memory (phoneNumber → OTP + expiry)
    private final Map<String, OtpDetails> otpStore = new HashMap<>();

    public String generateAndSendOtp(String phoneNumber) {
        Twilio.init(accountSid, authToken);

        String otp = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        otpStore.put(phoneNumber, new OtpDetails(otp, expiryTime));

        Message.creator(
                new PhoneNumber(phoneNumber), // To
                new PhoneNumber(twilioPhoneNumber), // From
                "Your OTP is: " + otp
        ).create();

        System.out.println("Phone number:"+phoneNumber);
        System.out.println("OTP:"+otp);
        return "OTP sent successfully";
    }

    public boolean validateOtp(String phoneNumber, String otp) {
        OtpDetails details = otpStore.get(phoneNumber);
        System.out.println("Phone number:"+phoneNumber);
        System.out.println("OTP:"+otp);
        if (details == null) return false;
        if (LocalDateTime.now().isAfter(details.expiryTime)) return false;

        return details.otp.equals(otp);
    }

    private static class OtpDetails {
        String otp;
        LocalDateTime expiryTime;

        OtpDetails(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }
}