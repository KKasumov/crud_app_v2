package com.kyamran.app.contoller;

import com.kyamran.app.model.Label;
import com.kyamran.app.model.Post;
import com.kyamran.app.model.PostStatus;
import com.kyamran.app.repository.PostRepository;
import com.kyamran.app.repository.gson.GsonPostRepositoryImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PostController {
    private final PostRepository postRepository = new GsonPostRepositoryImpl();


    public Post createPost(String content, List<Label> labels) {
        Post post = Post.builder()
                .content(content)
                .status(PostStatus.ACTIVE)
                .created(new Date())
                .updated(new Date())
                .labels(labels)
                .build();

        return postRepository.save(post);
    }

    public Post getPostById(Integer id) {
        return postRepository.getById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public void updatePost(Integer id, String content, List<Label> labels) {
        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.setCreated(new Date());
        post.setUpdated(new Date());
        post.setStatus(PostStatus.ACTIVE);
        post.setLabels(labels);

        postRepository.save(post);
    }

    public boolean deletePost(Integer id) {
        return postRepository.deleteById(id);
    }
    public Post addLabelToPost(Integer postId, Label label) {
        Post post = getPostById(postId);
        if (post == null) {
            return null;
        }
        post.getLabels().add(label);
        post.setUpdated(new Date());
        return postRepository.update(post);
    }

    public Post removeLabelFromPost(Integer postId, Integer labelId) {
        Post post = getPostById(postId);
        if (post == null) {
            return null;
        }
        post.getLabels().removeIf(label -> Objects.equals(label.getId(), labelId));
        post.setUpdated(new Date());
        return postRepository.update(post);
    }

    public Post updateLabelInPost(Integer postId, Label updatedLabel) {
        Post post = getPostById(postId);
        if (post == null) {
            return null;
        }
        post.getLabels().removeIf(label -> Objects.equals(label.getId(), updatedLabel.getId()));
        post.getLabels().add(updatedLabel);
        post.setUpdated(new Date());
        return postRepository.update(post);
    }

}
