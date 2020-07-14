package com.zeze.markdownppt.user.web;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeze.markdownppt.common.DefaultResponseDTO;
import com.zeze.markdownppt.note.web.NoteService;
import com.zeze.markdownppt.note.web.dto.NoteCreateRequestDTO;
import com.zeze.markdownppt.note.web.dto.NoteResponseDTOs;
import com.zeze.markdownppt.user.domain.GitHubClient;
import com.zeze.markdownppt.user.domain.LoginEmail;
import com.zeze.markdownppt.user.domain.User;
import com.zeze.markdownppt.user.domain.UserRepository;
import com.zeze.markdownppt.user.web.dto.CodeRequestDTO;
import com.zeze.markdownppt.user.web.dto.TokenResponseDTO;
import com.zeze.markdownppt.user.web.dto.UserInfoDTO;
import com.zeze.markdownppt.util.TokenProvider;

@Service
public class UserService {
    private final GitHubClient gitHubClient;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final NoteService noteService;

    public UserService(GitHubClient gitHubClient, UserRepository userRepository,
        TokenProvider tokenProvider, NoteService noteService) {
        this.gitHubClient = gitHubClient;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.noteService = noteService;
    }

    @Transactional
    public DefaultResponseDTO<TokenResponseDTO> createToken(CodeRequestDTO codeRequestDTO) {
        String gitHubToken = gitHubClient.getToken(codeRequestDTO.getCode()).getAccessToken();
        UserInfoDTO gitHubInfo = gitHubClient.getInfo(gitHubToken);
        User user = new User(gitHubInfo.getEmail(), gitHubInfo.getName());
        userRepository.save(user);
        String token = tokenProvider.createToken(gitHubInfo.getEmail());

        return DefaultResponseDTO.from(new TokenResponseDTO(token));
    }

    public DefaultResponseDTO<NoteResponseDTOs> findSlidesByEmail(LoginEmail loginEmail) {
        User user = userRepository.findByEmail(loginEmail.getEmail())
            .orElseThrow(AssertionError::new);
        NoteResponseDTOs noteResponseDTOs = noteService.findAllByUserId(user);
        return DefaultResponseDTO.from(noteResponseDTOs);
    }

    @Transactional
    public void addNote(LoginEmail loginEmail, NoteCreateRequestDTO noteCreateRequestDTO) {
        User user = userRepository.findByEmail(loginEmail.getEmail())
            .orElseThrow(AssertionError::new);
        noteService.addNote(user, noteCreateRequestDTO.getName(),
            noteCreateRequestDTO.getMarkdown());
    }
}
