package com.skillbridge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.skillbridge.dto.SkillMatchResponse;
import com.skillbridge.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillbridge.entity.Project;
import com.skillbridge.repository.ProjectRepository;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
    
    public Project updateProject(
            Long id,
            Project updatedProject
    ) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        project.setTitle(updatedProject.getTitle());
        project.setDescription(updatedProject.getDescription());
        project.setRequiredSkills(updatedProject.getRequiredSkills());
        project.setDeadline(updatedProject.getDeadline());

        return projectRepository.save(project);
    }

  
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public SkillMatchResponse calculateSkillMatch(User user,
                                                  Project project) {

        List<String> userSkills = Arrays.stream(user.getSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        List<String> requiredSkills = Arrays.stream(project.getRequiredSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        List<String> missingSkills = new ArrayList<>();

        int matched = 0;

        for (String skill : requiredSkills) {

            if (userSkills.contains(skill)) {
                matched++;
            } else {
                missingSkills.add(skill);
            }
        }

        int score = (matched * 100) / requiredSkills.size();

        return new SkillMatchResponse(score, missingSkills);
    }
    }
