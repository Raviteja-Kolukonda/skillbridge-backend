package com.skillbridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillbridge.entity.Project;
import com.skillbridge.service.ProjectService;
import com.skillbridge.dto.SkillMatchResponse;
import com.skillbridge.entity.User;
import com.skillbridge.repository.ProjectRepository;
import com.skillbridge.repository.UserRepository;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
    @GetMapping("/match/{userId}/{projectId}")
    public SkillMatchResponse getSkillMatch(@PathVariable Long userId,
                                            @PathVariable Long projectId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return projectService.calculateSkillMatch(user, project);
    }
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {

        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @RequestBody Project project
    ) {

        return projectService.updateProject(id, project);

    }
}