package com.project.booksoft.service;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.booksoft.exceptions.ProjectNotFoundException;
import com.project.booksoft.persistence.domain.Project;
import com.project.booksoft.persistence.repo.ProjectRepo;

@RunWith (SpringRunner.class)
public class ProjectServiceUnitTest {
	
	@InjectMocks
	private ProjectService service;
	
	@Mock
	private ProjectRepo repo;
	
	private List<Project> projectList;
	
	private Project testProject;
	
	private Project testProjectWithID;
	
	final long id = 1L;
	
	@Before
	public void init() {
		this.projectList = new ArrayList<>();
		this.projectList.add(testProject);
		this.testProject = new Project("Learn Java", "Resources to learn java");
		this.testProjectWithID = new Project(testProject.getName(), testProject.getDescription());
		this.testProjectWithID.setId(id);
	}
	
	@Test
	public void createProjectTest() {
		when(this.repo.save(testProject)).thenReturn(testProjectWithID);
		
		assertEquals(this.testProjectWithID, this.service.createProject(testProject));
		
		verify(this.repo, times(1)).save(this.testProject);
	}
		
	@Test
	public void deleteProjectTest() throws ProjectNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);
		
		this.service.deleteProject(id);
		
		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}
	
	@Test
	public void updateProjectTest() throws ProjectNotFoundException {
		Project newProject = new Project("Learn Python", "Resources to learn python");
		Project updatedProject = new Project(newProject.getName(), newProject.getDescription());
		updatedProject.setId(this.id);
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testProjectWithID));
		when(this.repo.save(updatedProject)).thenReturn(updatedProject);
		
		assertEquals(updatedProject, this.service.updateProject(updatedProject, this.id));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedProject);
	}
	
	@Test
	public void findProjectsByIDTest() throws ProjectNotFoundException {
		when(this.repo.findById(id)).thenReturn(Optional.of(this.testProjectWithID));
		
		assertEquals(this.testProjectWithID, this.service.findProjectById(this.id));
		
		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void findAllProjectsTest() {
		when(repo.findAll()).thenReturn(this.projectList);
		
		assertFalse("Controller has found no projects", this.service.readProjects().isEmpty());
		
		verify(repo, times(1)).findAll();
	}
	
}
