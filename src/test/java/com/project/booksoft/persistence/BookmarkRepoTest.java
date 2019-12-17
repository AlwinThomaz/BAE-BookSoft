package com.project.booksoft.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.booksoft.persistence.domain.Bookmark;
import com.project.booksoft.persistence.repo.BookmarkRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookmarkRepoTest {
	
	@Autowired
	private BookmarkRepo repo;
	
	private final String TEST_TYPE = "Software tools";
	
	private final Bookmark TEST_BOOKMARK = new Bookmark(TEST_TYPE, "Github", "Remote code storage repository", "https://github.com");
	
	private Bookmark testSavedBookmark;
	
	@Before
	public void init() {
		this.repo.deleteAll();
		this.testSavedBookmark = this.repo.save(this.TEST_BOOKMARK);
	}
	
	@Test
	public void testFindByName() {
		assertThat(this.repo.findByType(this.TEST_TYPE)).containsExactly(this.testSavedBookmark);
	}
}
