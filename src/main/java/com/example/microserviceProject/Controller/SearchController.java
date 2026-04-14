package com.example.microserviceProject.Controller;

import com.example.microserviceProject.Dto.ProfileDetailsDTO;
import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<List<User>> getUserLoginDetails(@RequestBody User user) {
        String userLoginId = user.getUserLoginId();
        String userName = user.getUserOfficialName();

        List<User> list = userService.getUserSearchdtls(userLoginId, userName);

        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/getSearchProfileDetails")
    public ResponseEntity<List<ProfileDetailsDTO>> getSearchProfileDetails(@RequestBody Map<String, String> body){
        Integer userId = Integer.valueOf(body.get("userId"));
        Integer searchUserId = Integer.valueOf(body.get("searchUserId"));
        List<ProfileDetailsDTO> getdetails = userService.getSearchProfileDetails(userId,searchUserId);
        if(getdetails!= null){
            return new ResponseEntity<>(getdetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
