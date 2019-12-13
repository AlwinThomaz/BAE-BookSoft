package com.project.booksoft.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.booksoft.exceptions.BookmarkNotFoundException;
import com.project.booksoft.persistence.domain.Bookmark;
import com.project.booksoft.service.BookmarkService;


@RestController
public class BookmarkController {
	
private BookmarkService service;
	
	@Autowired
	public BookmarkController(BookmarkService service) {
		super();
		this.service = service;
	}
	@PostMapping("/createBookmark")
	public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
		return this.service.createBookmark(bookmark);
	}

	@DeleteMapping("/deleteBookmark/{id}")
	public void deleteBookmark(@PathVariable Long id) throws BookmarkNotFoundException {
		this.service.deleteBookmark(id);
	}
	
	@GetMapping("/getBookmark/{id}")
	public Bookmark getBookmark(@PathVariable Long id) throws BookmarkNotFoundException {
		return this.service.findBookmarkByID(id);
	}
	
	@GetMapping("/getBookmark/{type}")
	public Bookmark getBookmark(@PathVariable String type) throws BookmarkNotFoundException {
		return this.service.findBookmarkByType(type);
	}

	@GetMapping("/getAllBookmark")
	public List<Bookmark> getAllBookmarks() {
		return this.service.readBookmarks();
	}

	@PutMapping("/updateBookmark")
	public Bookmark updateBookmark(@PathParam("id") Long id, @RequestBody Bookmark bookmark) throws BookmarkNotFoundException {
		return this.service.updateBookmark(bookmark, id);
	}

}



