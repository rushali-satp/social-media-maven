package com.example.microserviceProject.Controller;

import com.example.microserviceProject.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private AIService aiService;

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        String reply = aiService.getAIResponse(message);
        return Map.of("reply", reply);
    }
}
