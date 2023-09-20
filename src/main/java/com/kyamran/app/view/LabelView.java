package com.kyamran.app.view;

import com.kyamran.app.contoller.LabelController;
import com.kyamran.app.model.Label;

import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final LabelController labelController = new LabelController();
    private final Scanner scanner = new Scanner(System.in);

    public void createLabel() {
        System.out.println("Enter name: ");
        String labelName = scanner.nextLine();
        Label createdLabel = labelController.createLabel(labelName);
        System.out.println("Created label: " + createdLabel);
    }

    public void getLabelById() {
        System.out.println("Enter label id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Label label = labelController.getLabelById(id);
        if (label != null) {
            System.out.println("Label: " + label);
        } else {
            System.out.println("Label with id " + id + " not found.");
        }
    }

    public void getAllLabels() {
        List<Label> labels = labelController.getAllLabels();
        System.out.println(labels);
    }

    public void updateLabel() {
        System.out.println("Enter label id to update: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Label label = labelController.getLabelById(id);

        if (label != null) {
            System.out.println("Enter new label name: ");
            String newLabelName = scanner.nextLine();
            label.setName(newLabelName);
            labelController.updateLabel(String.valueOf(newLabelName));
            System.out.println("Updated label: " + label);
        } else {
            System.out.println("Label with id " + id + " not found.");
        }
    }

    public void deleteLabel() {
        System.out.println("Enter label id to delete: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        boolean isDeleted = labelController.deleteLabel(id);

        if (isDeleted) {
            System.out.println("Label with id " + id + " deleted successfully.");
        } else {
            System.out.println("Label with id " + id + " not found.");
        }
    }
}


