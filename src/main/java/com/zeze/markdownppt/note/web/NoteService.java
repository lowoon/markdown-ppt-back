package com.zeze.markdownppt.note.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zeze.markdownppt.note.domain.Note;
import com.zeze.markdownppt.note.domain.NoteRepository;
import com.zeze.markdownppt.note.web.dto.NoteResponseDTO;
import com.zeze.markdownppt.note.web.dto.NoteResponseDTOs;
import com.zeze.markdownppt.user.domain.User;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteResponseDTOs findAllByUserId(User user) {
        List<Note> notes = noteRepository.findAllByUserId(user.getId());

        return notes.stream()
            .map(Note::getMarkdown)
            .map(NoteResponseDTO::new)
            .collect(Collectors.collectingAndThen(Collectors.toList(), NoteResponseDTOs::new));
    }

    public void addNote(User user, String name, String markdown) {
        Note note = new Note(name, markdown);
        Note saved = noteRepository.save(note);
        saved.setUser(user);
    }
}
