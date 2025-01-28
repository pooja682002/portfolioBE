package com.example.portfoliotask_backend.controller;

import com.example.portfoliotask_backend.dto.ProjectMainDTO;
import com.example.portfoliotask_backend.model.ProjectMain;
import com.example.portfoliotask_backend.service.ProjectMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projectdetails")
@CrossOrigin(origins = "*")
public class ProjectMainController {

    @Autowired
    private ProjectMainService projectMainService;

    // Fetch all projects
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProjects() {
        List<ProjectMain> projects = projectMainService.getAllProjects();

        List<ProjectMainDTO> projectDTOs = projects.stream()
                .map(ProjectMainDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of(
                "statusCode", 200,
                "msg", "Projects fetched successfully",
                "response", projectDTOs
        ));
    }

    // Fetch project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProjectById(@PathVariable UUID id) {
        try {
            Optional<ProjectMain> project = projectMainService.getProjectById(id);

            if (project.isPresent()) {
                return ResponseEntity.ok(Map.of(
                        "statusCode", 200,
                        "msg", "Project fetched successfully",
                        "response", new ProjectMainDTO(project.get())
                ));
            } else {
                return ResponseEntity.status(404).body(Map.of(
                        "statusCode", 404,
                        "msg", "Project not found",
                        "response", null
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "statusCode", 500,
                    "msg", "Internal server error: " + e.getMessage(),
                    "response", null
            ));
        }
    }

    // Create a new project
    @PostMapping
    public ResponseEntity<Map<String, Object>> createProject(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String path,
            @RequestParam(required = false) byte[] image) {
        try {
            ProjectMain projectMain = new ProjectMain();
            projectMain.setTitle(title);
            projectMain.setDescription(description);
            projectMain.setPath(path);
            projectMain.setImage(image);

            ProjectMain savedProject = projectMainService.saveProject(projectMain);

            return ResponseEntity.status(201).body(Map.of(
                    "statusCode", 201,
                    "msg", "Project created successfully",
                    "response", new ProjectMainDTO(savedProject)
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "statusCode", 500,
                    "msg", "Error saving project: " + e.getMessage(),
                    "response", null
            ));
        }
    }

    // Update an existing project
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProject(
            @PathVariable UUID id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String path,
            @RequestParam(required = false) byte[] image) {
        try {
            ProjectMain existingProject = projectMainService.getProjectById(id)
                    .orElseThrow(() -> new RuntimeException("Project not found"));

            existingProject.setTitle(title);
            existingProject.setDescription(description);
            existingProject.setPath(path);
            if (image != null) {
                existingProject.setImage(image);
            }

            ProjectMain updatedProject = projectMainService.saveProject(existingProject);

            return ResponseEntity.ok(Map.of(
                    "statusCode", 200,
                    "msg", "Project updated successfully",
                    "response", new ProjectMainDTO(updatedProject)
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of(
                    "statusCode", 404,
                    "msg", e.getMessage(),
                    "response", null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "statusCode", 500,
                    "msg", "Error updating project: " + e.getMessage(),
                    "response", null
            ));
        }
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProject(@PathVariable UUID id) {
        try {
            projectMainService.deleteProject(id);

            return ResponseEntity.ok(Map.of(
                    "statusCode", 200,
                    "msg", "Project deleted successfully",
                    "response", Collections.emptyMap()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "statusCode", 500,
                    "msg", "Error deleting project: " + e.getMessage(),
                    "response", Collections.emptyMap()
            ));
        }
    }
}
