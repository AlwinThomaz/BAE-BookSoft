package com.project.booksoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.booksoft.exceptions.BookmarkNotFoundException;
import com.project.booksoft.persistence.domain.Bookmark;
import com.project.booksoft.persistence.repo.BookmarkRepo;


@Service
public class BookmarkService {

private BookmarkRepo repo;
	
	@Autowired
	public BookmarkService(BookmarkRepo repo) {
		this.repo = repo;
	}
	
	public Bookmark createBookmark(Bookmark bookmark) {
		return this.repo.save(bookmark);
	}
	
	public boolean deleteBookmark(Long id) throws BookmarkNotFoundException {
		if (!this.repo.existsById(id)) {
			throw new BookmarkNotFoundException();
		}
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	
	public Bookmark findBookmarkByID(Long id) throws BookmarkNotFoundException {
		return this.repo.findById(id).orElseThrow(
				() -> new BookmarkNotFoundException());
	}
	
	public List<Bookmark> readBookmarks() {
		return this.repo.findAll();
	}

	public Bookmark updateBookmark(Bookmark bookmark, Long id) throws BookmarkNotFoundException {
		Bookmark toUpdate = findBookmarkByID(id);
		toUpdate.setType(bookmark.getType());
		toUpdate.setName(bookmark.getName());
		toUpdate.setDescription(bookmark.getDescription());
		toUpdate.setUrl(bookmark.getUrl());
		return this.repo.save(toUpdate);
	}

}


