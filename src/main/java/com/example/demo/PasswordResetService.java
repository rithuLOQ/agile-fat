package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordResetService {

    // In a real app, use Redis or a Database table to store OTPs with timestamps
    private final Map<String, String> otpCache = new ConcurrentHashMap<>();

    /**
     * Generates a 6-digit OTP and stores it.
     */
    public String initiateReset(String email) {
        // 1. Generate a random 6-digit number
        String otp = String.format("%06d", new Random().nextInt(1000000));
        
        // 2. Store it (Key: Email, Value: OTP)
        // Note: In production, add a 'created_at' timestamp to check for expiration
        otpCache.put(email, otp);
        
        // 3. Return it so the EmailService can send it out
        return otp;
    }

    /**
     * Validates the OTP and updates the password.
     */
    public boolean validateAndReset(String email, String userEnteredOtp, String newPassword) {
        String storedOtp = otpCache.get(email);

        if (storedOtp != null && storedOtp.equals(userEnteredOtp)) {
            // Success: Update the user's password in the Database here
            // passwordEncoder.encode(newPassword);
            
            otpCache.remove(email); // Clear OTP after use
            return true;
        }
        return false; // Invalid OTP or email
    }
}