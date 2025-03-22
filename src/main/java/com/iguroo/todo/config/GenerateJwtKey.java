package com.iguroo.todo.config;

import io.jsonwebtoken.security.Keys;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateJwtKey {
    public static void main(String[] args) {
        byte[] key = new byte[32]; // 256-bit key
        new SecureRandom().nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        System.out.println("New Secure JWT Secret: " + encodedKey);
    }
}
