package com.kyamran.app.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyamran.app.model.Label;
import com.kyamran.app.repository.LabelRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GsonLabelRepositoryImpl implements LabelRepository {
    private final String filePath = "src/main/resources/gson/labels.json";
    private final Gson gson = new Gson();

    @Override
    public Label getById(Integer id) {
        return getAllLabels().stream()
                .filter(label -> Objects.equals(label.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Label> getAll() {
        return getAllLabels();
    }

    @Override
    public Label save(Label labelToCreate) {
        List<Label> labels = getAllLabels();
        Integer maxId = generateId(labels);
        labelToCreate.setId(maxId);
        labels.add(labelToCreate);
        saveAllLabels(labels);
        return labelToCreate;
    }

    @Override
    public Label update(Label labelToUpdate) {
        List<Label> labels = getAllLabels()
                .stream()
                .map(exisitingLabel -> {
                    if(Objects.equals(exisitingLabel.getId(), labelToUpdate.getId())) {
                        return labelToUpdate;
                    }
                    return exisitingLabel;
                }).toList();
        saveAllLabels(labels);
        return labelToUpdate;
    }

    @Override
    public boolean deleteById(Integer id) {
        List<Label> labels = getAllLabels();
        labels.removeIf(label -> Objects.equals(label.getId(), id));
        saveAllLabels(labels);
        return false;
    }

    private List<Label> getAllLabels() {
        try (Reader reader = new FileReader(filePath)) {
            Type type = new TypeToken<List<Label>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveAllLabels(List<Label> labels) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(labels, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateId(List<Label> labels) {

        return labels.stream().mapToInt(Label::getId).max().orElse(0) + 1;
    }
}
