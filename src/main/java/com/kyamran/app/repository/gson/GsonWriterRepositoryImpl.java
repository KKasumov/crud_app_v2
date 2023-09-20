package com.kyamran.app.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyamran.app.model.Writer;
import com.kyamran.app.repository.WriterRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final String filePath = "src/main/resources/gson/writers.json";
    private final Gson gson = new Gson();

    @Override
    public Writer getById(Integer id) {
        List<Writer> writers = getAllWriters();
        return writers.stream()
                .filter(writer -> Objects.equals(writer.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        return getAllWriters();
    }

    @Override
    public Writer save(Writer writerToCreate) {
        List<Writer> writers = getAllWriters();
        writers.add(writerToCreate);
        saveAllWriters(writers);
        return writerToCreate;
    }

    @Override
    public Writer update(Writer writerToUpdate) {
        List<Writer> writers = getAllWriters();
        writers.stream()
                .filter(w -> Objects.equals(w.getId(), writerToUpdate.getId()))
                .findFirst()
                .ifPresent(w -> {
                    w.setFirstName(writerToUpdate.getFirstName());
                    w.setLastName(writerToUpdate.getLastName());
                    w.setPosts(writerToUpdate.getPosts());
                });
        saveAllWriters(writers);
        return writerToUpdate;
    }

    @Override
    public boolean deleteById(Integer id) {
        List<Writer> writers = getAllWriters();
        writers.removeIf(writer -> Objects.equals(writer.getId(), id));
        saveAllWriters(writers);
        return false;
    }

    private List<Writer> getAllWriters() {
        try (Reader reader = new FileReader(filePath)) {
            Type type = new TypeToken<List<Writer>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveAllWriters(List<Writer> writers) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(writers, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
