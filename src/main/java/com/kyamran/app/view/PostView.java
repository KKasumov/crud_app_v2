package com.kyamran.app.view;

import com.kyamran.app.contoller.LabelController;
import com.kyamran.app.contoller.PostController;
import com.kyamran.app.model.Label;
import com.kyamran.app.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
    private final Scanner scanner = new Scanner(System.in);

    public void createPost() {
        System.out.println("Enter content: ");
        String content = scanner.nextLine();
        System.out.println("Prepare labels");
        List<Label> labels = prepareLabels();

        Post createdPost = postController.createPost(content, labels);
        System.out.println("Created post: " + createdPost);
    }

    private List<Label> prepareLabels() {
        List<Label> availableLabels = labelController.getAllLabels();
        List<Label> selectedLabels = new ArrayList<>();

        while (true) {
            System.out.println("Available Labels: ");
            availableLabels.forEach(label -> System.out.println(label.getId() + ": " + label.getName()));

            System.out.println("Enter ID (-1 to finish selecting labels): ");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == -1) {
                break;
            }

            Label currentSelected = labelController.getLabelById(input);
            if (currentSelected != null) {
                selectedLabels.add(currentSelected);
                availableLabels.remove(currentSelected);
            } else {
                System.out.println("Invalid ID. Please try again.");
            }
        }

        return selectedLabels;
    }

    public void getPostById() {
        System.out.println("Enter post id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Post post = postController.getPostById(id);
        if (post != null) {
            System.out.println("Post: " + post);
        } else {
            System.out.println("Post with id " + id + " not found.");
        }
    }

    public void getAllPosts() {
        List<Post> posts = postController.getAllPosts();
        System.out.println(posts);
    }

    public void updatePost() {
        System.out.println("Enter post id to update: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Post post = postController.getPostById(id);

        if (post != null) {
            System.out.println("Enter new content: ");
            String newContent = scanner.nextLine();
            System.out.println("Prepare new labels");
            List<Label> labels = prepareLabels();

            postController.updatePost(id, newContent, labels);
            System.out.println("Updated post: " + post);
        } else {
            System.out.println("Post with id " + id + " not found.");
        }
    }

    public void deletePost() {
        System.out.println("Enter post id to.delete: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        boolean isDeleted = postController.deletePost(id);

        if (isDeleted) {
            System.out.println("Post with id " + id + " deleted successfully.");
        } else {
            System.out.println("Post with id " + id + " not found.");
        }
    }
    public void addLabelToPost() {
        System.out.println("Enter post ID: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter label ID: ");
        Integer labelId = scanner.nextInt();
        scanner.nextLine();

        Label label = labelController.getLabelById(labelId);
        if (label == null) {
            System.out.println("Label with id " + labelId + " not found.");
            return;
        }

        Post updatedPost = postController.addLabelToPost(postId, label);
        if (updatedPost == null) {
            System.out.println("Post with id " + postId + " not found.");
        } else {
            System.out.println("Updated post: " + updatedPost);
        }
    }

    public void removeLabelFromPost() {
        System.out.println("Enter post ID: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter label ID to remove: ");
        Integer labelId = scanner.nextInt();
        scanner.nextLine();

        Post updatedPost = postController.removeLabelFromPost(postId, labelId);
        if (updatedPost == null) {
            System.out.println("Post with id " + postId + " not found.");
        } else {
            System.out.println("Updated post: " + updatedPost);
        }
    }

    public void updateLabelInPost() {
        System.out.println("Enter post ID: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter label ID to update: ");
        Integer labelId = scanner.nextInt();
        scanner.nextLine();

        Label label = labelController.getLabelById(labelId);
        if (label == null) {
            System.out.println("Label with id " + labelId + " not found.");
            return;
        }

        System.out.println("Enter new label name: ");
        String newLabelName = scanner.nextLine();
        label.setName(newLabelName);

        Post updatedPost = postController.updateLabelInPost(postId, label);
        if (updatedPost == null) {
            System.out.println("Post with id " + postId + " not found.");
        } else {
            System.out.println("Updated post: " + updatedPost);
        }
    }
}
