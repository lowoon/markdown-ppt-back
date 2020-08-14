package com.zeze.markdownppt.note.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/test")
    public void test() {
        log.trace("Trace Level 테스트");
        log.debug("DEBUG Level 테스트");
        log.info("INFO Level 테스트");
        log.warn("Warn Level 테스트");
        log.error("ERROR Level 테스트");
    }
}
