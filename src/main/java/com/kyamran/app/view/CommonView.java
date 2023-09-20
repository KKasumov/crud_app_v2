package com.kyamran.app.view;

import java.util.Scanner;

public class CommonView {
    private final LabelView labelView;
    private final PostView postView;
    private final WriterView writerView;
    private final Scanner scanner;

    public CommonView() {
        this.labelView = new LabelView();
        this.postView = new PostView();
        this.writerView = new WriterView();
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Work with Labels");
            System.out.println("2. Work with Posts");
            System.out.println("3. Work with Writers");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> showLabelMenu();
                case 2 -> showPostMenu();
                case 3 -> showWriterMenu();
                case 4 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Incorrect option. Please, try again.");
            }
        }
    }

    private void showLabelMenu() {
        while (true) {
            System.out.println("Label menu:");
            System.out.println("1. Create a Label");
            System.out.println("2. Get Label by ID");
            System.out.println("3. Get all Labels");
            System.out.println("4. Update a Label");
            System.out.println("5. Delete a Label");
            System.out.println("6. Return to Main menu");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> labelView.createLabel();
                case 2 -> labelView.getLabelById();
                case 3 -> labelView.getAllLabels();
                case 4 -> labelView.updateLabel();
                case 5 -> labelView.deleteLabel();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Incorrect option. Please, try again.");
            }
        }
    }

    private void showPostMenu() {
        while (true) {
            System.out.println("Post menu:");
            System.out.println("1. Create a Post");
            System.out.println("2. Get Post by ID");
            System.out.println("3. Get all Posts");
            System.out.println("4. Update a Post");
            System.out.println("5. Delete a Post");
            System.out.println("6. Add a Label to a Post");
            System.out.println("7. Remove a Label from a Post");
            System.out.println("8. Update a Label in a Post");
            System.out.println("9. Return to Main menu");
            System.out.println("10. Return to Main menu");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> postView.createPost();
                case 2 -> postView.getPostById();
                case 3 -> postView.getAllPosts();
                case 4 -> postView.updatePost();
                case 5 -> postView.deletePost();
                case 6 -> postView.addLabelToPost();
                case 7 -> postView.removeLabelFromPost();
                case 8 -> postView.updateLabelInPost();
                case 9 -> {
                    return;
                }
                default -> System.out.println("Incorrect option. Please, try again.");
            }
        }
    }

    private void showWriterMenu() {
        while (true) {
            System.out.println("Writer menu:");
            System.out.println("1. Create a Writer");
            System.out.println("2. Get Writer by ID");
            System.out.println("3. Get all Writers");
            System.out.println("4. Update a Writer");
            System.out.println("5. Delete a Writer");
            System.out.println("6. Find a Post by Writer and ID");
            System.out.println("7. Add a Post to a Writer");
            System.out.println("8. Update a Post of a Writer");
            System.out.println("9. Delete a Post of a Writer");
            System.out.println("10. Return to Main menu");


            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> writerView.createWriter();
                case 2 -> writerView.getWriterById();
                case 3 -> writerView.getAllWriters();
                case 4 -> writerView.updateWriter();
                case 5 -> writerView.deleteWriter();
                case 6 -> writerView.findPostByWriterAndId();
                case 7 -> writerView.addPostToWriter();
                case 8 -> writerView.updatePostOfWriter();
                case 9 -> writerView.deletePostOfWriter();
                case 10 -> {
                    return;
                }
                default -> System.out.println("Incorrect option. Please, try again.");
            }
        }
    }
}
