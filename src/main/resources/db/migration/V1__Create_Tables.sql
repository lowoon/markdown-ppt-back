CREATE TABLE slide
(
    id           BIGINT AUTO_INCREMENT,
    created_at   DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at   DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    access_level VARCHAR(255),
    content      LONGTEXT,
    title        VARCHAR(255),
    user_id      BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE member
(
    id              bigint AUTO_INCREMENT,
    created_at      DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at      DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    social_provider VARCHAR(20),
    social_id       VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE authority
(
    id   bigint AUTO_INCREMENT,
    role VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE member_authorities
(
    user_id        BIGINT NOT NULL,
    authorities_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, authorities_id)
);

CREATE TABLE member_resource
(
    id            BIGINT AUTO_INCREMENT,
    created_at    DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at    DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    email         VARCHAR(255),
    name          VARCHAR(255),
    profile_image VARCHAR(255),
    user_id       BIGINT,
    PRIMARY KEY (id)
);

ALTER TABLE member_resource
    ADD CONSTRAINT uk_member_resource_user_id UNIQUE (user_id);

ALTER TABLE member_authorities
    ADD CONSTRAINT uk_member_authorities_authorities_id
        FOREIGN KEY (authorities_id)
            REFERENCES authority (id);

ALTER TABLE member_authorities
    ADD CONSTRAINT member_authorities_user_id
        FOREIGN KEY (user_id)
            REFERENCES member (id);

INSERT INTO authority (role)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');
