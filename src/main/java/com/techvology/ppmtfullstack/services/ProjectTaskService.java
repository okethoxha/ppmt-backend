package com.techvology.ppmtfullstack.services;

import com.techvology.ppmtfullstack.domain.ProjectTask;
import com.techvology.ppmtfullstack.repositories.BacklogRepository;
import com.techvology.ppmtfullstack.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(){
        //PTs to be added to a specific project, project != null, BL exists
        //set the bl to pt

        //we want our project sequence to be like this: IDPRO-1
        // Update the Backlog sequence

        //Initial priority when priority null
        // Initial status when status is null
    }
}
