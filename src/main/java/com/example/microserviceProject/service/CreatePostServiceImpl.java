package com.example.microserviceProject.service;

import com.example.microserviceProject.entity.CreatePost;
import com.example.microserviceProject.entity.PostComments;
import com.example.microserviceProject.entity.PostLikes;
import com.example.microserviceProject.entity.ProfileDetails;
import com.example.microserviceProject.repository.CreatePostRepository;
import com.example.microserviceProject.repository.PostCommentsRepository;
import com.example.microserviceProject.repository.PostLikeRepository;
import com.example.microserviceProject.repository.ProfileDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreatePostServiceImpl implements CreatePostService{

    @Autowired
    private CreatePostRepository createPostRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostCommentsRepository postCommentsRepository;

    @Autowired
    private ProfileDetailsRepository profileDetailsRepository;

    private final String UPLOAD_DIR = "D:/uploadsPostImage/"; // change path if needed

    public String createPost(String userLoginId, Integer userId, String postText, MultipartFile image, Integer likes
    ) throws IOException {

        CreatePost post = new CreatePost();
        post.setUserLoginId(userLoginId);
        post.setUserId(userId);
        post.setPostText(postText);
        post.setLikes(likes);
        post.setActive(true);
        post.setCreatedOn(LocalDateTime.now());

        if (image != null && !image.isEmpty()) {

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());

            post.setPostImageName(fileName);
            post.setPostImagePath(filePath.toString());
            post.setPostImageSize(String.valueOf(image.getSize())); // bytes
        }

        createPostRepository.save(post);
        ProfileDetails profileDetails = profileDetailsRepository.findByUserId(userId);

        if (profileDetails != null) {
            Integer totalPost = profileDetails.getTotalPost() == null ? 0 : profileDetails.getTotalPost();
            profileDetails.setTotalPost(totalPost + 1);

            profileDetailsRepository.save(profileDetails);
        }
        return "Post created successfully";
    }

    @Transactional
    public String likePost(Integer postId, Integer userId) {

        // check if already liked
        if (postLikeRepository.existsByPostIdAndUserId(postId, userId)) {
            return "ALREADY_LIKED";
        }

        // save like
        PostLikes like = new PostLikes();
        like.setPostId(postId);
        like.setUserId(userId);
        postLikeRepository.save(like);

        Integer totalLikes = postLikeRepository.countLikesByPostId(postId);

        createPostRepository.updateLikeCount(postId, totalLikes);

        return "LIKED_SUCCESSFULLY";
    }

    @Override
    public String commentPost(Integer postId, Integer userId, String commentText) {

        CreatePost post = createPostRepository.findById(Long.valueOf(postId))
                .orElseThrow(() -> new RuntimeException("Post not found"));
        PostComments comment = new PostComments();
        comment.setPost(post);
        comment.setUserId(userId);
        comment.setCommentText(commentText);

        postCommentsRepository.save(comment);

        return "COMMENT_SUCCESSFULLY";
    }

    @Override
    public List<CreatePost> getPrfilePostDetails(Integer userId) {
        return createPostRepository.getProfilePostDetails(userId);
    }

    @Override
    @Transactional
    public boolean deletePost(Integer postId) {
        int updatedRows = createPostRepository.softDeletePost(postId);
        return updatedRows > 0;
    }

    public List<CreatePost> getPostDetails() {
        return createPostRepository.findByIsActiveTrue();
    }



}
