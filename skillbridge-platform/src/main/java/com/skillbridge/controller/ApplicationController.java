package com.skillbridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillbridge.entity.Application;
import com.skillbridge.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public Application applyProject(@RequestBody Application application) {
        return applicationService.applyProject(application);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUser(@PathVariable Long userId) {
        return applicationService.getApplicationsByUser(userId);
    }
    @GetMapping
    public List<Application> getAllApplications() {

        return applicationService.getAllApplications();

    }
    @PutMapping("/{applicationId}/{status}")
    public Application updateStatus(
            @PathVariable Long applicationId,
            @PathVariable String status
    ) {

        return applicationService.updateStatus(
                applicationId,
                status
        );
    }
    
}