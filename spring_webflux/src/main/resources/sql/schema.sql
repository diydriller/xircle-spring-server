DROP TABLE IF EXISTS user_room;
DROP TABLE IF EXISTS user_hashtag;
DROP TABLE IF EXISTS hashtag;
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS follow;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users(
    id BIGINT AUTO_INCREMENT,
    age INT NOT NULL,
    adjective VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    profile_img_src VARCHAR(100) NOT NULL,
    introduction VARCHAR(200),
    job VARCHAR(50) NOT NULL,
    display_name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    university VARCHAR(100) NOT NULL,
    is_public TINYINT(1) NOT NULL,
    is_graduate TINYINT(1) NOT NULL,
    phone_number VARCHAR(100) NOT NULL,
    work_place VARCHAR(100),
    resume VARCHAR(200),
    is_location_public TINYINT(1) NOT NULL,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS follow(
    id BIGINT AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    target_member_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(member_id) REFERENCES users(id),
    FOREIGN KEY(target_member_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS post(
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    title VARCHAR(50) NOT NULL,
    post_img_src VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS hashtag(
    id BIGINT AUTO_INCREMENT,
    hashtag VARCHAR(50) NOT NULL,
    post_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS user_hashtag(
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    hashtag_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(hashtag_id) REFERENCES hashtag(id)
);


CREATE TABLE IF NOT EXISTS room(
    id BIGINT AUTO_INCREMENT,
    type VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS user_room(
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS chat(
    id BIGINT AUTO_INCREMENT,
    room_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(room_id) REFERENCES room(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);






