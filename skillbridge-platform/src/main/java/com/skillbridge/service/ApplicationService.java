package com.skillbridge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillbridge.entity.Application;
import com.skillbridge.repository.ApplicationRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application applyProject(Application application) {

        boolean alreadyApplied =
                applicationRepository
                        .existsByUserIdAndProjectId(
                                application.getUserId(),
                                application.getProjectId()
                        );

        if (alreadyApplied) {

        	throw new IllegalStateException(
        		    "You have already applied for this project"
        		);
        }

        application.setStatus("APPLIED");

        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId);
    }
    public List<Application> getAllApplications() {

        return applicationRepository.findAll();

    }
    
    public Application updateStatus(
            Long applicationId,
            String status
    ) {

        Application application =
                applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

        application.setStatus(status);

        return applicationRepository.save(application);
    }
}