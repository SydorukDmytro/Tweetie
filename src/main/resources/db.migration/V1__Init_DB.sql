
CREATE TABLE hibernate_sequence(
    next_val INT NOT NULL
) engine=InnoDB;

INSERT INTO hibernate_sequence values ( 0 );


CREATE TABLE message (
    id BIGINT not null,
    filename VARCHAR(255),
    tag VARCHAR(255),
    text VARCHAR(255),
    user_id BIGINT,
    primary key (id)) engine=InnoDB;


CREATE TABLE user_role (
    user_id BIGINT NOT NULL ,
    roles VARCHAR(255)) engine=InnoDB;


CREATE TABLE usr (
    id BIGINT NOT NULL,
    activation_code VARCHAR(255),
    active BIT NOT NULL ,
    email VARCHAR(255),
    password VARCHAR(255),
    username VARCHAR(255),
    primary key (id)) engine=InnoDB;


ALTER TABLE message
ADD CONSTRAINT message_user_fk
FOREIGN KEY (user_id) REFERENCES usr (id);

ALTER TABLE user_role
ADD CONSTRAINT user_role_user_fk
FOREIGN KEY (user_id) REFERENCES usr (id);