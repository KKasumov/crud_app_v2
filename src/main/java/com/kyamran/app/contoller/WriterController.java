package com.kyamran.app.contoller;

import com.kyamran.app.model.Post;
import com.kyamran.app.model.Writer;
import com.kyamran.app.repository.WriterRepository;
import com.kyamran.app.repository.gson.GsonWriterRepositoryImpl;

import java.util.List;
import java.util.Objects;

public class WriterController {
    private final WriterRepository writerRepository = new GsonWriterRepositoryImpl();

    public Writer getById(Integer id) {
        return writerRepository.getById(id);
    }

    public Writer createWriter(String firstName, String lastName) {
        Writer writer = new Writer();
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        return writerRepository.save(writer);
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public Writer updateWriter(Integer id, String firstName, String lastName,List<Post> posts) {
        Writer writer = new Writer();
        writer.setId(id);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(posts);
        return writerRepository.update(writer);
    }

    public void deleteWriterById(Integer id) {
        writerRepository.deleteById(id);
    }

    public Post findPostByWriterAndId(Integer writerId, Integer postId) {
        Writer writer = getById(writerId);
        if (writer == null) {
            return null;
        }
        return writer.getPosts().stream()
                .filter(post -> Objects.equals(post.getId(), postId))
                .findFirst()
                .orElse(null);
    }

    public Writer addPostToWriter(Integer writerId, Post post) {
        Writer writer = getById(writerId);
        if (writer == null) {
            return null;
        }
        writer.getPosts().add(post);
        return updateWriter(writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getPosts());
    }

    public Writer updatePostOfWriter(Integer writerId, Post post) {
        Writer writer = getById(writerId);
        if (writer == null) {
            return null;
        }
        writer.getPosts().removeIf(p -> Objects.equals(p.getId(), post.getId()));
        writer.getPosts().add(post);
        return updateWriter(writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getPosts());
    }

    public Writer deletePostOfWriter(Integer writerId, Integer postId) {
        Writer writer = getById(writerId);
        if (writer == null) {
            return null;
        }
        writer.getPosts().removeIf(post -> Objects.equals(post.getId(), postId));
        return updateWriter(writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getPosts());
    }
}
