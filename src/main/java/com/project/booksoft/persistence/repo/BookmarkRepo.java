package com.project.booksoft.persistence.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.booksoft.persistence.domain.Bookmark;

public interface BookmarkRepo extends JpaRepository<Bookmark, Long> {

	Optional<Bookmark> findByType(String type);



}

