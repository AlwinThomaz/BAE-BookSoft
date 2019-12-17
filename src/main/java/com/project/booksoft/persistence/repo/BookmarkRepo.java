package com.project.booksoft.persistence.repo;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.booksoft.persistence.domain.Bookmark;

public interface BookmarkRepo extends JpaRepository<Bookmark, Long> {

	List<Bookmark> findByType(String type);



}

