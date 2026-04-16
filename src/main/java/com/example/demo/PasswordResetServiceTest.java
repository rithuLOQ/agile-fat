package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PasswordResetServiceTest {
    private PasswordResetService service = new PasswordResetService();
    @Test
    void testInvalidOTP() {
        String email = "test@example.com";
        service.initiateReset(email);
        boolean result = service.validateAndReset(email, "000000", "newPass123");
        assertFalse(result, "Reset should fail with an incorrect OTP.");
    }
    @Test
    void testNonExistentEmail() {
        boolean result = service.validateAndReset( "unknown@example.com", "123456", "newPass123");
        assertFalse(result, "Reset should fail if email is not in cache.");
    }
}