package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.CreatePost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CreatePostService {

    public String createPost(String userLoginId, Integer userId, String postText, MultipartFile image , Integer likes)throws IOException;


    String likePost(Integer postId, Integer userId);

    public List<CreatePost> getPostDetails();

    String commentPost(Integer postId, Integer userId, String commentText);

    List<CreatePost> getPrfilePostDetails(Integer userId);

    boolean deletePost(Integer postId);
}
