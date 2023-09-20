package com.kyamran.app.contoller;

import com.kyamran.app.model.Label;
import com.kyamran.app.model.PostStatus;
import com.kyamran.app.repository.LabelRepository;
import com.kyamran.app.repository.gson.GsonLabelRepositoryImpl;

import java.util.Date;
import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();

    public Label getLabelById(Integer id) {
        return labelRepository.getById(id);
    }


    public Label createLabel(String name) {
        Label label = new Label();
        label.setName(name);
        label.setCreated(new Date());
        label.setUpdated(new Date());
        label.setStatus(PostStatus.ACTIVE);
        return labelRepository.save(label);
    }
    public void updateLabel(String name) {
        Label label = new Label();
        label.setName(name);
        label.setCreated(new Date());
        label.setUpdated(new Date());
        label.setStatus(PostStatus.ACTIVE);
        labelRepository.save(label);
    }

    public List<Label> getAllLabels() {
        return labelRepository.getAll();
    }

    public boolean deleteLabel(Integer id) {
        return labelRepository.deleteById(id);
    }
}
