package com.techvology.ppmtfullstack.repositories;

import com.techvology.ppmtfullstack.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

}
