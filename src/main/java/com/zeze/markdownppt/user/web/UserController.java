package com.zeze.markdownppt.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeze.markdownppt.common.DefaultResponseDTO;
import com.zeze.markdownppt.note.web.dto.NoteCreateRequestDTO;
import com.zeze.markdownppt.note.web.dto.NoteResponseDTOs;
import com.zeze.markdownppt.user.domain.LoginEmail;
import com.zeze.markdownppt.user.web.auth.LoginUser;
import com.zeze.markdownppt.user.web.dto.CodeRequestDTO;
import com.zeze.markdownppt.user.web.dto.TokenResponseDTO;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<DefaultResponseDTO<TokenResponseDTO>> login(
        @RequestBody CodeRequestDTO codeRequestDTO) {
        return ResponseEntity.ok().body(userService.createToken(codeRequestDTO));
    }

    @GetMapping("/me/notes")
    public ResponseEntity<DefaultResponseDTO<NoteResponseDTOs>> list(
        @LoginUser LoginEmail loginEmail) {
        return ResponseEntity.ok().body(userService.findSlidesByEmail(loginEmail));
    }

    @PostMapping("/me/notes")
    public ResponseEntity<Void> addSlide(@LoginUser LoginEmail loginEmail,
        @RequestBody NoteCreateRequestDTO noteCreateRequestDTO) {
        userService.addNote(loginEmail, noteCreateRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
