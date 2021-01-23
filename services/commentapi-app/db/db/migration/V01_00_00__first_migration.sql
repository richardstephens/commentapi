CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    email varchar(255) NOT NULL,
    password varchar(255) NULL,
    admin tinyint NOT NULL DEFAULT 0,
    created DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pages (
    id INT NOT NULL AUTO_INCREMENT,
    url_hash varchar(48),
    url varchar(255),
    PRIMARY KEY (id),
    INDEX (url_hash),
    UNIQUE (url_hash)
);

CREATE TABLE comments (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    parent_comment_id INT NULL,
    page_id INT NOT NULL,
    created DATETIME NOT NULL,
    comment TEXT NOT NULL,
    approved TINYINT NOT NULL,

    PRIMARY KEY (id),

    INDEX (user_id),
    INDEX (page_id),

    FOREIGN KEY (user_id)
                      REFERENCES users(id),
    FOREIGN KEY (page_id)
                      REFERENCES pages(id)
);

