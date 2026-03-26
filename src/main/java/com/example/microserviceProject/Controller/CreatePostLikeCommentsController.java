package com.example.microserviceProject.Controller;

import com.example.microserviceProject.entity.CreatePost;
import com.example.microserviceProject.entity.User;
import com.example.microserviceProject.service.CreatePostService;
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
@RequestMapping("/api/createPost")
public class CreatePostLikeCommentsController {

    @Autowired
    private CreatePostService createPostService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createPost(
            @RequestParam String userLoginId,
            @RequestParam Integer userId,
            @RequestParam(required = false) String postText,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam Integer likes
    ) {
        try {
            String response = createPostService.createPost(
                    userLoginId, userId, postText, image , likes
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while creating post");
        }
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(
            @PathVariable Integer postId,
            @RequestBody Map<String, Integer> body) {

        Integer userId = body.get("userId");

        String result = createPostService.likePost(postId, userId);

        if ("ALREADY_LIKED".equals(result)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("You already liked this post");
        }

        return ResponseEntity.ok("Post liked successfully");
    }


    @PostMapping("/{postId}/comment")
    public ResponseEntity<String> commentPost(
            @PathVariable Integer postId,
            @RequestBody Map<String, String> body) {

        Integer userId = Integer.valueOf(body.get("userId"));
        String commentText = body.get("comment");

        createPostService.commentPost(postId, userId, commentText);

        return ResponseEntity.ok("Comment added successfully");
    }



    @GetMapping("/getPost")
    public ResponseEntity<List<CreatePost>> getPost(){
        List<CreatePost> getdetails = createPostService.getPostDetails();
        if(getdetails!= null){
            return new ResponseEntity<>(getdetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/getProfilePost")
    public ResponseEntity<List<CreatePost>> getProfilePost(@RequestBody Map<String, String> body){
        Integer userId = Integer.valueOf(body.get("userId"));
        List<CreatePost> getdetails = createPostService.getPrfilePostDetails(userId);
        if(getdetails!= null){
            return new ResponseEntity<>(getdetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        boolean isDelete = createPostService.deletePost(postId);

        if (isDelete) {
            return ResponseEntity.ok("Post deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Post not found");
        }
    }
}
