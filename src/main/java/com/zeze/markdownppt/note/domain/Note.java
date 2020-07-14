package com.zeze.markdownppt.note.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.zeze.markdownppt.common.BaseEntity;
import com.zeze.markdownppt.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Note extends BaseEntity {
    private String name;
    private String markdown;

    @ManyToOne
    private User user;

    public Note(String name, String markdown) {
        this.name = name;
        this.markdown = markdown;
    }

    public void save(String markdown) {
        this.markdown = markdown;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
