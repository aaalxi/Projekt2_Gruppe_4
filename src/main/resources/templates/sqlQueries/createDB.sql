DROP DATABASE IF EXISTS Onskeskyen;
CREATE DATABASE Onskeskyen;

       USE Onskeskyen;
DROP TABLE IF EXISTS SharedWishlist;
DROP TABLE IF EXISTS Wish;
DROP TABLE IF EXISTS Wishlist;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users
(
    user_id          INT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    email            VARCHAR(255) NOT NULL UNIQUE,
    password_hash    VARCHAR(255) NOT NULL,
    user_create_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Wishlist
(
    wishlist_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT          NOT NULL, -- FK --
    title           VARCHAR(255) NOT NULL,
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME,              -- CONSIDER IF WE NEED THIS ONE--
    share_token     VARCHAR(64)  UNIQUE NULL,
    -- FK CONSTRAINT --
    CONSTRAINT fk_wishlist_users FOREIGN KEY (user_id) REFERENCES Users (user_id) ON DELETE CASCADE
);

CREATE TABLE Wish
(
    wish_id             INT AUTO_INCREMENT PRIMARY KEY,
    wishlist_id         INT          NOT NULL, -- FK --
    title               VARCHAR(255) NOT NULL,
    description         VARCHAR(500),
    product_url         VARCHAR(2048),
    price               DECIMAL(10, 2),
    wish_amount         INT          NOT NULL DEFAULT 1,
    img_url             VARCHAR(2048),
    priority            INT,
    created_at          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reserved_by_user_id INT NULL,              -- FK --

    CONSTRAINT fk_wish_wishlist FOREIGN KEY (wishlist_id) REFERENCES Wishlist (wishlist_id) ON DELETE CASCADE,
    CONSTRAINT fk_wish_reservedUser FOREIGN KEY (reserved_by_user_id) REFERENCES Users (user_id) ON DELETE SET NULL,

    -- CHECK CONSTRAINT - DATA INTEGRETY --
    CONSTRAINT chk_wish_amount CHECK (wish_amount > 0),
    CONSTRAINT chk_wish_price CHECK (price IS NULL OR price >= 0)
);

CREATE TABLE SharedWishlist
(
    share_id            INT AUTO_INCREMENT PRIMARY KEY,
    wishlist_id         INT NOT NULL, -- FK --
    shared_with_user_id INT NOT NULL, -- FK --

    CONSTRAINT fk_shared_wishlist FOREIGN KEY (wishlist_id) REFERENCES Wishlist (wishlist_id) ON DELETE CASCADE,
    CONSTRAINT fk_shared_users FOREIGN KEY (shared_with_user_id) REFERENCES Users (user_id) ON DELETE CASCADE,
    -- NO DUPLICATES IN COMBINATION TABLE --
    CONSTRAINT unq_shared_wishlist_user UNIQUE (wishlist_id, shared_with_user_id)
);







