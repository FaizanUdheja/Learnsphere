package com.bhlearnsphere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @PostMapping("/checkout")
    public ResponseEntity<?> processCheckout(@RequestBody Map<String, Object> checkoutData) {
        // Mock implementation - in real app, integrate with payment gateway
        String courseId = checkoutData.get("courseId").toString();
        String userId = checkoutData.get("userId").toString();
        String amount = checkoutData.get("amount").toString();
        
        return ResponseEntity.ok(Map.of(
            "success", true,
            "transactionId", "TXN_" + System.currentTimeMillis(),
            "message", "Payment processed successfully",
            "courseId", courseId,
            "userId", userId,
            "amount", amount
        ));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> paymentData) {
        // Mock implementation
        return ResponseEntity.ok(Map.of(
            "verified", true,
            "message", "Payment verified successfully"
        ));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable Long userId) {
        // Mock implementation
        return ResponseEntity.ok(Map.of(
            "payments", new Object[]{
                Map.of("id", 1, "courseId", 1, "amount", 49.99, "status", "completed"),
                Map.of("id", 2, "courseId", 2, "amount", 79.99, "status", "completed")
            }
        ));
    }
}
