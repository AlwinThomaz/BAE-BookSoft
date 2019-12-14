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
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.project.booksoft.exceptions.BookmarkNotFoundException;
import com.project.booksoft.persistence.domain.Bookmark;
import com.project.booksoft.service.BookmarkService;


public class BookmarkControllerUnitTest {
	

	@InjectMocks
	private BookmarkController controller;

	@Mock
	private BookmarkService service;

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
		when(this.service.createBookmark(testBookmark)).thenReturn(testBookmarkWithID);

		assertEquals(this.testBookmarkWithID, this.controller.createBookmark(testBookmark));

		verify(this.service, times(1)).createBookmark(this.testBookmark);
	}

	@Test
	public void deleteBookmarkTest() throws BookmarkNotFoundException {
		this.controller.deleteBookmark(id);

		verify(this.service, times(1)).deleteBookmark(id);
	}
	
	@Test
	public void updateBookmarkTest() throws BookmarkNotFoundException {
		
		Bookmark newBookmark = new Bookmark("ProgrammingCourses", "Udemy", "Java online course", "https://www.udemy.com/topic/java/");
		Bookmark updatedBookmark = new Bookmark(newBookmark.getType(), newBookmark.getName(), newBookmark.getDescription(), newBookmark.getUrl());
		updatedBookmark.setId(this.id);

		when(this.service.updateBookmark(newBookmark, this.id)).thenReturn(updatedBookmark);

		assertEquals(updatedBookmark, this.controller.updateBookmark(this.id, newBookmark));

		verify(this.service, times(1)).updateBookmark(newBookmark, this.id);
	}

	@Test
	public void findBookmarksByIDTest() throws BookmarkNotFoundException {
		when(this.service.findBookmarkById(this.id)).thenReturn(this.testBookmarkWithID);

		assertEquals(this.testBookmarkWithID, this.controller.getBookmark(this.id));

		verify(this.service, times(1)).findBookmarkById(this.id);
	}

	@Test
	public void findAllBookmarksTest() {

		when(service.readBookmarks()).thenReturn(this.bookmarkList);

		assertFalse("Controller has found no bookmarks", this.controller.getAllBookmarks().isEmpty());

		verify(service, times(1)).readBookmarks();
	}

	
}

