package com.example.microserviceProject.Controller;

import com.example.microserviceProject.Dto.ProfileDetailsDTO;
import com.example.microserviceProject.entity.CreatePost;
import com.example.microserviceProject.entity.ProfileDetails;
import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.form.BaseForm;
import com.example.microserviceProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String message = userService.createUser(user);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        if(user!= null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<List<User>> getUserLoginDetails(@RequestBody User user) {
        List<User> list = userService.getUserLoginDetails(user.getUserLoginId(),user.getPassword());
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createProfileDetails(
            @ModelAttribute BaseForm request)
    {
        try {
            String response = userService.createProfileDetails(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while creating post");
        }
    }

    @PostMapping("/getProfileDetails")
    public ResponseEntity<List<ProfileDetails>> getProfileDetails(@RequestBody Map<String, String> body){
        Integer userId = Integer.valueOf(body.get("userId"));
        List<ProfileDetails> getdetails = userService.getPostDetails(userId);
        if(getdetails!= null){
            return new ResponseEntity<>(getdetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/getALLProfileDetails")
    public ResponseEntity<List<ProfileDetailsDTO>> getALLProfileDetails(@RequestBody Map<String, String> body){
        Integer userId = Integer.valueOf(body.get("userId"));
        List<ProfileDetailsDTO> getdetails = userService.getALLProfileDetails(userId);
        if(getdetails!= null){
            return new ResponseEntity<>(getdetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/getRequestProfileDetails")
    public ResponseEntity<List<ProfileDetailsDTO>> getRequestProfileDetails(@RequestBody Map<String, String> body){
        Integer userId = Integer.valueOf(body.get("userId"));
        List<ProfileDetailsDTO> getdetails = userService.getRequestProfileDetails(userId);
        if(getdetails!= null){
            return new ResponseEntity<>(getdetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/acceptRequestProfile")
    public ResponseEntity<String> acceptRequestProfile (@RequestBody BaseForm baseForm){
        try {
            String response = userService.acceptRequestProfile(baseForm);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while creating post");
        }
    }
}
