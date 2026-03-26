package com.example.microserviceProject.Controller;

import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.entity.UserRequest;
import com.example.microserviceProject.form.BaseForm;
import com.example.microserviceProject.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/userRequest")
public class UserRequestController {

    @Autowired
    private UserRequestService userRequestService;

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, int code) {

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("msgcode", code);
        errorResponse.put("status", "error");
        errorResponse.put("message", message);

        return ResponseEntity.status(code).body(errorResponse);
    }

    @PostMapping("/sendRequest")
    public ResponseEntity<?> sendRequest(@RequestBody UserRequest userRequest) {
        try{
        String message = userRequestService.sendRequest(userRequest);
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("msgcode", 200);
        successResponse.put("status", "success");
        successResponse.put("message", message);
        return ResponseEntity.ok(successResponse);
        }
        catch (Exception e)
        {
            return buildErrorResponse("Something went wrong. Please try again.", 401);
        }
    }

    @PostMapping("/getRequest")
    public ResponseEntity<?> getUser(@RequestBody UserRequest userRequest) {
        try {
            List<UserRequest> user = userRequestService.getUser(userRequest);
            if(user == null || user.isEmpty()){
                return buildErrorResponse("No user found",404);
            }

            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("msgcode", 200);
            successResponse.put("status", "success");
            successResponse.put("data", user);

            return ResponseEntity.ok(successResponse);
        }
        catch (Exception e)
        {
            return buildErrorResponse("Something went wrong. Please try again.", 401);
        }

    }

    @PostMapping("/removeFromFollowing")
    public ResponseEntity<?> removeFromFollowing(@RequestBody BaseForm userRequest) {
        try {
            String user = userRequestService.removeFromFollowing(userRequest);
            if(user == null || user.isEmpty()){
                return buildErrorResponse("No user found",404);
            }

            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("msgcode", 200);
            successResponse.put("status", "success");
            successResponse.put("data", user);

            return ResponseEntity.ok(successResponse);
        }
        catch (Exception e)
        {
            return buildErrorResponse("Something went wrong. Please try again.", 401);
        }

    }
}
