package com.example.portfoliotask_backend.controller;

import com.example.portfoliotask_backend.dto.SkillDTO;
import com.example.portfoliotask_backend.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "*")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping
    public ResponseEntity<?> addSkill(@RequestParam("name") String name, @RequestParam("logo") MultipartFile logo) {
        try {
            byte[] logoBytes = logo.getBytes();
            SkillDTO savedSkill = skillService.addSkill(name, logoBytes);
            return ResponseEntity.ok().body(createResponse(200, "Skill added successfully", savedSkill));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse(400, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable UUID id, @RequestParam("name") String name, @RequestParam("logo") MultipartFile logo) {
        try {
            byte[] logoBytes = logo.getBytes();
            SkillDTO updatedSkill = skillService.updateSkill(id, name, logoBytes);
            return ResponseEntity.ok().body(createResponse(200, "Skill updated successfully", updatedSkill));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse(400, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable UUID id) {
        try {
            skillService.deleteSkill(id);
            return ResponseEntity.ok().body(createResponse(200, "Skill deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse(400, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSkills() {
        List<SkillDTO> skills = skillService.getAllSkills();
        return ResponseEntity.ok().body(createResponse(200, "Skills fetched successfully", skills));
    }

    private Map<String, Object> createResponse(int statusCode, String message, Object response) {
        return Map.of(
                "statusCode", statusCode,
                "message", message,
                "response", response
        );
    }
}
