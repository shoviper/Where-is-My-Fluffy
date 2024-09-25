-- Drop tables if they exist
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS post;
-- DROP TABLE IF EXISTS pet;
-- DROP TABLE IF EXISTS user;

-- Create Users Table
CREATE TABLE IF NOT EXISTS `users` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `phone` VARCHAR(20),
    `location_id` INT
);

-- Create Pets Table
CREATE TABLE IF NOT EXISTS `pets` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `age` FLOAT NOT NULL,
    `description` TEXT NOT NULL,
    `status` VARCHAR(20) NOT NULL CHECK (status IN ('MISSING', 'FOUND')),
    `owner_id` INT,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

-- Create Posts Table
CREATE TABLE IF NOT EXISTS `posts` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `pet_id` INT,
    `owner_id` INT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`pet_id`) REFERENCES `pets`(id) ON DELETE CASCADE,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(id) ON DELETE CASCADE
);
