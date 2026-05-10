package com.example.microserviceProject.Controller;

import com.example.microserviceProject.Dto.StatusDTO;
import com.example.microserviceProject.entity.StatusEntity;
import com.example.microserviceProject.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, int code) {

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("msgcode", code);
        errorResponse.put("status", "error");
        errorResponse.put("message", message);

        return ResponseEntity.status(code).body(errorResponse);
    }

    @PostMapping("/addStatus")
    public ResponseEntity<?> addStatus(@RequestBody StatusEntity status) {
        try{
            String message = statusService.addStatus(status);
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

    @GetMapping("/getAllStatus")
    public ResponseEntity<?> getAllStatus() {
        try{
            List<StatusDTO> list = statusService.getAllStatus();
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("msgcode", 200);
            successResponse.put("status", "success");
            successResponse.put("message", list);
            return ResponseEntity.ok(successResponse);
        }
        catch (Exception e)
        {
            return buildErrorResponse("Something went wrong. Please try again.", 401);
        }
    }
}
