package com.kyamran.app.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyamran.app.model.Post;
import com.kyamran.app.repository.PostRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GsonPostRepositoryImpl implements PostRepository {
    private final String filePath = "src/main/resources/gson/posts.json";
    private final Gson gson = new Gson();

    @Override
    public Post getById(Integer id) {
        return getAllPosts().stream()
                .filter(post -> Objects.equals(post.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return getAllPosts();
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = getAllPosts();
        Integer maxId = generateId(posts);
        post.setId(maxId);
        posts.add(post);
        saveAllPosts(posts);
        return post;
    }

    @Override
    public Post update(Post postToUpdate) {
        List<Post> posts = getAllPosts()
                .stream()
                .map(exisitingPost -> {
                    if(Objects.equals(exisitingPost.getId(), postToUpdate.getId())) {
                        return postToUpdate;
                    }
                    return exisitingPost;
                }).toList();
        saveAllPosts(posts);
        return postToUpdate;
    }

    @Override
    public boolean deleteById(Integer id) {
        List<Post> posts = getAllPosts();
        posts.removeIf(post -> Objects.equals(post.getId(), id));
        saveAllPosts(posts);
        return false;
    }

    private List<Post> getAllPosts() {
        try (Reader reader = new FileReader(filePath)) {
            Type type = new TypeToken<List<Post>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveAllPosts(List<Post> posts) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(posts, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateId(List<Post> posts) {
        return posts.stream().mapToInt(Post::getId).max().orElse(0) + 1;
    }
}
