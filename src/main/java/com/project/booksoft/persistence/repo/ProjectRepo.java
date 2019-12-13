package com.project.booksoft.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.booksoft.persistence.domain.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
