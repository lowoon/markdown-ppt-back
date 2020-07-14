package com.zeze.markdownppt.user.domain;

import javax.persistence.Entity;

import com.zeze.markdownppt.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends BaseEntity {
    private String email;
    private String name;
}
