package com.project.booksoft.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.booksoft.persistence.domain.Project;


public interface ProjectRepo extends JpaRepository<Project, Long> {

	List<Project> findByName(String name);

}
