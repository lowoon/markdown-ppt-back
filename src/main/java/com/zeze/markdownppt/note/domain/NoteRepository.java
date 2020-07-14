package com.zeze.markdownppt.note.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserId(Long userId);
}
