package com.kyamran.app.view;

import com.kyamran.app.contoller.WriterController;
import com.kyamran.app.model.Post;
import com.kyamran.app.model.PostStatus;
import com.kyamran.app.model.Writer;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController = new WriterController();

    private final Scanner scanner = new Scanner(System.in);

    public void createWriter() {
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        Writer createdWriter = writerController.createWriter(firstName, lastName);
        System.out.println("Created writer: " + createdWriter);
    }

    public void getAllWriters() {
        List<Writer> writers = writerController.getAllWriters();
        System.out.println(writers);
    }

    public void updateWriter() {
        System.out.println("Enter writer ID: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter new last name: ");
        String lastName = scanner.nextLine();
        Writer updatedWriter = writerController.updateWriter(id, firstName, lastName);
        System.out.println("Updated writer: " + updatedWriter);
    }

    public void deleteWriter() {
        System.out.println("Enter writer ID: ");
        Integer id = scanner.nextInt();
        writerController.deleteWriterById(id);
        System.out.println("Writer deleted.");
    }
    public void getWriterById() {
        System.out.println("Enter the ID of the writer: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Writer writer = writerController.getById(id);
        System.out.println("Found writer: " + writer);
    }

    public void findPostByWriterAndId() {
        System.out.println("Enter writer ID: ");
        Integer writerId = scanner.nextInt();
        System.out.println("Enter post ID: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();
        Post post = writerController.findPostByWriterAndId(writerId, postId);
        System.out.println("Found post: " + post);
    }

    public void addPostToWriter() {
        System.out.println("Enter writer ID: ");
        Integer writerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter post ID: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter post content: ");
        String content = scanner.nextLine();

        Post post = new Post();
        post.setId(postId);
        post.setContent(content);
        post.setCreated(new Date());
        post.setUpdated(new Date());
        post.setStatus(PostStatus.ACTIVE);

        Writer updatedWriter = writerController.addPostToWriter(writerId, post);
        System.out.println("Updated writer: " + updatedWriter);
    }


    public void updatePostOfWriter() {
        System.out.println("Enter writer ID: ");
        Integer writerId = scanner.nextInt();
        System.out.println("Enter post ID: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new post content: ");
        String newContent = scanner.nextLine();

        Post post = new Post();
        post.setId(postId);
        post.setContent(newContent);
        post.setUpdated(new Date());

        Writer updatedWriter = writerController.updatePostOfWriter(writerId, post);
        System.out.println("Updated writer: " + updatedWriter);
    }

    public void deletePostOfWriter() {
        System.out.println("Enter writer ID: ");
        Integer writerId = scanner.nextInt();

        System.out.println("Enter post ID: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();

        Writer updatedWriter = writerController.deletePostOfWriter(writerId, postId);
        System.out.println("Updated writer: " + updatedWriter);
    }
}
