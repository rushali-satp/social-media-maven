package com.example.microserviceProject.service;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${groq.api.key}")
    private String apiKey;

    public String getAIResponse(String userMessage) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = """
        {
          "model": "llama-3.3-70b-versatile",
          "messages": [
            {
              "role": "system",
              "content": "You are a helpful AI assistant."
            },
            {
              "role": "user",
              "content": "%s"
            }
          ]
        }
        """.formatted(userMessage);

        HttpEntity<String> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(
                        "https://api.groq.com/openai/v1/chat/completions",
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

        Map body = response.getBody();

        List choices = (List) body.get("choices");

        Map choice = (Map) choices.get(0);

        Map message = (Map) choice.get("message");

        return message.get("content").toString();
    }
}
