package com.project.booksoft.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.booksoft.persistence.domain.Bookmark;

@RunWith (SpringRunner.class)
public class BookmarkServiceUnitTest {
	
	@InjectMocks
	private BookmarkService service;
	
	@Mock
	private Bookmark repo;
	
	private List<Bookmark> bookmarkList;
	
	private Bookmark testBookmark;
	
	private Bookmark testBookmarkWithId;
	
	final long id = 1L;
	
	@Before
	public void init() {
		this.bookmarkList = new ArrayList<>();
		this.bookmarkList.add(testBookmark);
		
	}

}
