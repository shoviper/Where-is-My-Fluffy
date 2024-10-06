-- -- Drop tables if they exist
-- DROP TABLE IF EXISTS `posts`;
-- DROP TABLE IF EXISTS `pets`;
-- DROP TABLE IF EXISTS `users`;

-- Create Locations Table
CREATE TABLE IF NOT EXISTS locations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(255)
);

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    user_image VARCHAR(255),
    user_location_id INT,
    FOREIGN KEY (`user_location_id`) REFERENCES `locations`(`id`) ON DELETE CASCADE
);

-- Create Animal type Table
CREATE TABLE IF NOT EXISTS animal_type (
    type VARCHAR(255) PRIMARY KEY
);

-- Create Pet image Table
CREATE TABLE IF NOT EXISTS pet_images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image_base64 TEXT
);

-- Create Pets Table
CREATE TABLE IF NOT EXISTS pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age DOUBLE NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('MISSING', 'FOUND')),
    location_id INT,
    owner_id INT,
    animal_type VARCHAR(255),
    founder_id INT,
    pet_image_id INT,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`animal_type`) REFERENCES `animal_type`(`type`) ON DELETE CASCADE,
    FOREIGN KEY (`location_id`) REFERENCES `locations`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`founder_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`pet_image_id`) REFERENCES `pet_images`(`id`) ON DELETE CASCADE
);

-- Create Posts Table
CREATE TABLE IF NOT EXISTS posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    type enum('MISSING', 'FOUND', 'ALERT'),
    pet_id INT,
    owner_id INT,
    reward_amount DOUBLE DEFAULT 0.0,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`pet_id`) REFERENCES `pets`(id) ON DELETE CASCADE,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(id) ON DELETE CASCADE
);

-- Create Notification image Table
CREATE TABLE IF NOT EXISTS notification_images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image_base64 TEXT
);

-- Create Notification Table
CREATE TABLE IF NOT EXISTS notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message VARCHAR(255) NOT NULL,
    type enum('NOTIFICATION_CREATED', 'NOTIFICATION_MODIFIED', 'NOTIFICATION_REMOVED', 'NOTIFICATION_PENDING', 'NOTIFICATION_APPROVED', 'NOTIFICATION_REJECTED' ),
    owner_id INT,
    sender_id INT,
    notification_image_id INT,
    reward_amount_to_pay DOUBLE DEFAULT 0.0,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(id) ON DELETE CASCADE,
    FOREIGN KEY (`sender_id`) REFERENCES `users`(id) ON DELETE CASCADE,
    FOREIGN KEY (`notification_image_id`) REFERENCES `notification_images`(`id`) ON DELETE CASCADE
);

-- Insert animal type into the animal_type table
INSERT INTO animal_type (type) VALUES ('dog');
INSERT INTO animal_type (type) VALUES ('cat');
INSERT INTO animal_type (type) VALUES ('rabbit');
INSERT INTO animal_type (type) VALUES ('bird');
INSERT INTO animal_type (type) VALUES ('crocodile');
INSERT INTO animal_type (type) VALUES ('fish');
INSERT INTO animal_type (type) VALUES ('lion');
INSERT INTO animal_type (type) VALUES ('ant');
INSERT INTO animal_type (type) VALUES ('turtle');
INSERT INTO animal_type (type) VALUES ('monkey');
INSERT INTO animal_type (type) VALUES ('frog');
INSERT INTO animal_type (type) VALUES ('pig');