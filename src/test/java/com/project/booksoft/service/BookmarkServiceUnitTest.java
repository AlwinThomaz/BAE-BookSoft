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

import com.project.booksoft.exceptions.BookmarkNotFoundException;
import com.project.booksoft.persistence.domain.Bookmark;
import com.project.booksoft.persistence.repo.BookmarkRepo;

@RunWith (SpringRunner.class)
public class BookmarkServiceUnitTest {
	
	@InjectMocks
	private BookmarkService service;
	
	@Mock
	private BookmarkRepo repo;
	
	private List<Bookmark> bookmarkList;
	
	private Bookmark testBookmark;
	
	private Bookmark testBookmarkWithID;
	
	final long id = 1L;
	
	
	@Before
	public void init() {
		this.bookmarkList = new ArrayList<>();
		this.bookmarkList.add(testBookmark);
		this.testBookmark = new Bookmark("CommunityForums", "Freecodecamp", "Place to discuss and learn coding", "https://www.freecodecamp.org");
		this.testBookmarkWithID = new Bookmark(testBookmark.getType(), testBookmark.getName(), testBookmark.getDescription(), testBookmark.getUrl());
		this.testBookmarkWithID.setId(id);		
	}
	
	@Test
	public void createBookmarkTest() {
		when(this.repo.save(testBookmark)).thenReturn(testBookmarkWithID);
		
		assertEquals(this.testBookmarkWithID, this.service.createBookmark(testBookmark));
		
		verify(this.repo, times(1)).save(this.testBookmark);
	}
		
	@Test
	public void deleteBookmarkTest() throws BookmarkNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);
		
		this.service.deleteBookmark(id);
		
		verify(this.repo, times(1)).deleteById(id);
		verify(this.repo, times(2)).existsById(id);
	}
	
	@Test
	public void updateBookmarkTest() throws BookmarkNotFoundException {
		Bookmark newBookmark = new Bookmark("ProgrammingCourses", "Udemy", "Java online course", "https://www.udemy.com/topic/java/");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getType(), newBookmark.getName(), newBookmark.getDescription(), newBookmark.getUrl());
		updatedBookmark.setId(this.id);
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testBookmarkWithID));
		when(this.repo.save(updatedBookmark)).thenReturn(updatedBookmark);
		
		assertEquals(updatedBookmark, this.service.updateBookmark(updatedBookmark, this.id));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedBookmark);
	}
	
	@Test
	public void findBookmarksByIDTest() throws BookmarkNotFoundException {
		when(this.repo.findById(id)).thenReturn(Optional.of(this.testBookmarkWithID));
		
		assertEquals(this.testBookmarkWithID, this.service.findBookmarkById(this.id));
		
		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void findAllBookmarksTest() {
		when(repo.findAll()).thenReturn(this.bookmarkList);
		
		assertFalse("Controller has found no bookmarks", this.service.readBookmarks().isEmpty());
		
		verify(repo, times(1)).findAll();
	}
	
}

