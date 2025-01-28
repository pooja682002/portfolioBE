package com.example.portfoliotask_backend.repository;

import com.example.portfoliotask_backend.model.ProjectMain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProjectMainRepository extends JpaRepository<ProjectMain, UUID> {
}
