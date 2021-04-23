package com.techvology.ppmtfullstack.services;

import com.techvology.ppmtfullstack.domain.Backlog;
import com.techvology.ppmtfullstack.domain.Project;
import com.techvology.ppmtfullstack.domain.ProjectTask;
import com.techvology.ppmtfullstack.exceptions.ProjectNotFoundException;
import com.techvology.ppmtfullstack.repositories.BacklogRepository;
import com.techvology.ppmtfullstack.repositories.ProjectRepository;
import com.techvology.ppmtfullstack.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

       try{
           //PTs to be added to a specific project, project != null, BL exists
           Backlog  backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
           //set the bl to pt
           projectTask.setBacklog(backlog);
           //we want our project sequence to be like this: IDPRO-1
           Integer BacklogSequence = backlog.getPTSequence();
           // Update the Backlog sequence
           BacklogSequence++;
           backlog.setPTSequence(BacklogSequence);
           // Add Sequence to Project Task
           projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
           projectTask.setProjectIdentifer(projectIdentifier);


           if(projectTask.getStatus()=="" || projectTask.getStatus() == null){
               projectTask.setStatus("TO_DO");
           }

           if (projectTask.getPriority() == null) {
               projectTask.setPriority(3);
           }

           return projectTaskRepository.save(projectTask);
         }catch (Exception e){
            throw new ProjectNotFoundException("Project with id: "+projectIdentifier+" could not be found!");
         }
    }

    public Iterable<ProjectTask> findBacklogById(String id){

        Project project = projectRepository.findByProjectIdentifier(id);

        if(project == null){
            throw new ProjectNotFoundException("Project with ID: '"+id+"' does not exist!");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id){

        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog == null){
            throw new ProjectNotFoundException("Project with ID: '"+backlog_id+"' does not exist!");
        }

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' could not be found.");
        }

        if(!projectTask.getProjectIdentifer().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist in project '"+backlog_id);
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTaskRepository.delete(projectTask);
    }
}
