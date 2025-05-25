package com.chat.securechat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class KeyController {

    private final String PUBLIC_KEY = "su41QyZE+a/Fozl1R8kZlxFZjQVPFQ3C+PcMJGBPMHk=";

    @GetMapping("/public-key")
    public ResponseEntity<Map<String, String>> getPublicKey(){
        return ResponseEntity.ok(Collections.singletonMap("key", PUBLIC_KEY));
    }
}
