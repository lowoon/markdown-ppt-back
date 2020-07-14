package com.zeze.markdownppt.note.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoteCreateRequestDTO {
    private String name;
    private String markdown;
}
