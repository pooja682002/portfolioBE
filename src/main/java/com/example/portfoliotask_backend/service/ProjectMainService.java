package com.example.portfoliotask_backend.service;

import com.example.portfoliotask_backend.model.ProjectMain;
import com.example.portfoliotask_backend.repository.ProjectMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectMainService {

    @Autowired
    private ProjectMainRepository projectMainRepository;

    // Fetch all projects
    public List<ProjectMain> getAllProjects() {
        return projectMainRepository.findAll();
    }

    // Fetch a project by its ID
    public Optional<ProjectMain> getProjectById(UUID id) {
        return projectMainRepository.findById(id);
    }

    // Add a new project
    public ProjectMain saveProject(ProjectMain projectMain) {
        return projectMainRepository.save(projectMain);
    }

    // Update an existing project by ID
    public ProjectMain updateProject(UUID id, ProjectMain updatedProjectMain) {
        return projectMainRepository.findById(id).map(existingProjectMain -> {
            existingProjectMain.setTitle(updatedProjectMain.getTitle());
            existingProjectMain.setDescription(updatedProjectMain.getDescription());
            existingProjectMain.setPath(updatedProjectMain.getPath());
            existingProjectMain.setImage(updatedProjectMain.getImage());
            return projectMainRepository.save(existingProjectMain);
        }).orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    // Delete a project by its ID
    public void deleteProject(UUID id) {
        if (!projectMainRepository.existsById(id)) {
            throw new RuntimeException("Project not found with id: " + id);
        }
        projectMainRepository.deleteById(id);
    }
}
