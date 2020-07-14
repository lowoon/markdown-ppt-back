package com.zeze.markdownppt.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DefaultResponseDTO<T> {
    private T data;
    private Integer code;
    private String message;

    public static <T> DefaultResponseDTO<T> from(T data) {
        return new DefaultResponseDTO<T>(data, null, null);
    }
}
