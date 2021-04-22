package com.techvology.ppmtfullstack.services;

import com.techvology.ppmtfullstack.domain.Backlog;
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

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //PTs to be added to a specific project, project != null, BL exists
        Backlog  backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        //set the bl to pt
        projectTask.setBacklog(backlog);
        //we want our project sequence to be like this: IDPRO-1
        Integer BacklogSequence = backlog.getPTSequence();
        // Update the Backlog sequence
        BacklogSequence++;
        // Add Sequence to Project Task
        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
        projectTask.setProjectIdentifer(projectIdentifier);

        //Initial priority when priority null
//        if (projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
//            projectTask.setPriority(3);
//        }
        // Initial status when status is null
        if(projectTask.getStatus()=="" || projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);
    }
}
