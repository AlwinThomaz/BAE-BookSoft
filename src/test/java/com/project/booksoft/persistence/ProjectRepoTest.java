package com.project.booksoft.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.booksoft.persistence.domain.Project;
import com.project.booksoft.persistence.repo.ProjectRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepoTest {

	@Autowired
	private ProjectRepo repo;
	
	private final String TEST_DESCRIPTION = "Resources to learn java";
	
	private final Project TEST_PROJECT = new Project("Java", TEST_DESCRIPTION);
	
	private Project testSavedProject;
	
	@Before
	public void init() {
		this.repo.deleteAll();
		this.testSavedProject = this.repo.save(this.TEST_PROJECT);
	}
	
	@Test
	public void testFindByName() {
		assertThat(this.repo.findByDescription(this.TEST_DESCRIPTION)).containsExactly(this.testSavedProject);
	}
}


