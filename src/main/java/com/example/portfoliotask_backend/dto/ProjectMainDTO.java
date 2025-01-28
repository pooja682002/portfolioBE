package com.example.portfoliotask_backend.dto;

import java.util.UUID;



import com.example.portfoliotask_backend.model.ProjectMain;
import java.util.UUID;

public class ProjectMainDTO {
    private UUID id;
    private String title;
    private String description;
    private String path;
    private byte[] image;

    // Constructor to map from the model to the DTO
    public ProjectMainDTO(ProjectMain projectMain) {
        this.id = projectMain.getId();
        this.title = projectMain.getTitle();
        this.description = projectMain.getDescription();
        this.path = projectMain.getPath();
        this.image = projectMain.getImage();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
