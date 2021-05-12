package com.techvology.ppmtfullstack.repositories;


import com.techvology.ppmtfullstack.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Iterable<Project> findAll();

    Project findByProjectIdentifier(String projectId);

    Iterable<Project> findAllByProjectLeader(String username);
}
