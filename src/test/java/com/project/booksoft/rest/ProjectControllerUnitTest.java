package com.project.booksoft.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.booksoft.exceptions.ProjectNotFoundException;
import com.project.booksoft.persistence.domain.Project;
import com.project.booksoft.service.ProjectService;

@RunWith(SpringRunner.class)
public class ProjectControllerUnitTest {

	@InjectMocks
	private ProjectController controller;

	@Mock
	private ProjectService service;

	private List<Project> projectList;

	private Project testProject;

	private Project testProjectWithID;

	final long id = 1L;

	@Before
	public void init() {
		this.projectList = new ArrayList<>();
		this.testProject = new Project("Learn Java", "Resources to learn java");
		this.testProjectWithID = new Project(testProject.getName(), testProject.getDescription());
		this.testProjectWithID.setId(id);
		this.projectList.add(testProject);
	}

	@Test
	public void createProjectTest() {
		when(this.service.createProject(testProject)).thenReturn(testProjectWithID);

		assertEquals(this.testProjectWithID, this.controller.createProject(testProject));

		verify(this.service, times(1)).createProject(this.testProject);
	}

	@Test
	public void deleteProjectTest() throws ProjectNotFoundException {
		this.controller.deleteProject(id);

		verify(this.service, times(1)).deleteProject(id);
	}

	@Test
	public void updateProjectTest() throws ProjectNotFoundException {

		Project newProject = new Project("Learn Python", "Resources to learn python");
		Project updatedProject = new Project(newProject.getName(), newProject.getDescription());
		updatedProject.setId(this.id);

		when(this.service.updateProject(newProject, this.id)).thenReturn(updatedProject);

		assertEquals(updatedProject, this.controller.updateProject(this.id, newProject));

		verify(this.service, times(1)).updateProject(newProject, this.id);
	}

	@Test
	public void findProjectsByIDTest() throws ProjectNotFoundException {
		when(this.service.findProjectById(this.id)).thenReturn(this.testProjectWithID);

		assertEquals(this.testProjectWithID, this.controller.getProject(this.id));

		verify(this.service, times(1)).findProjectById(this.id);
	}

	@Test
	public void getAllProjectsTest() {

		when(service.getAllProjects()).thenReturn(this.projectList);

		assertFalse("Controller has found no projects", this.controller.getAllProjects().isEmpty());

		verify(service, times(1)).getAllProjects();
	}

}
