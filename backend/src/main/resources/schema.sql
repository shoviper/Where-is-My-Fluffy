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
    phone VARCHAR(20)
);

-- Create Users Table
--CREATE TABLE IF NOT EXISTS users (
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    email VARCHAR(255) NOT NULL UNIQUE,
--    phone VARCHAR(20),
--    location_id INT NULL
--    FOREIGN KEY (`location_id`) REFERENCES `locations`(`id`) ON DELETE CASCADE
--);


-- Create Animal type Table
CREATE TABLE IF NOT EXISTS animal_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255)
);

-- Create Pets Table
CREATE TABLE IF NOT EXISTS pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age FLOAT NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('MISSING', 'FOUND')),
    location_id INT,
    owner_id INT,
    animal_type_id INT,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`animal_type_id`) REFERENCES `animal_type`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`location_id`) REFERENCES `locations`(`id`) ON DELETE CASCADE
);

-- Create Posts Table
CREATE TABLE IF NOT EXISTS posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type enum('Missing', 'Found', 'Alert'),
    pet_id INT,
    owner_id INT,
    FOREIGN KEY (`pet_id`) REFERENCES `pets`(id) ON DELETE CASCADE,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(id) ON DELETE CASCADE
);

-- Create Images Table
CREATE TABLE IF NOT EXISTS images (
    url VARCHAR(255) PRIMARY KEY,
    post_id INT,
    FOREIGN KEY (`post_id`) REFERENCES `posts`(`id`) ON DELETE CASCADE
);

-- Insert locations into the locations table
INSERT INTO locations (address) VALUES ('San Francisco');
INSERT INTO locations (address) VALUES ('Kmitl');

-- Insert users into the user table (without specifying the id, let AUTO_INCREMENT handle it)
INSERT INTO users (name, email, phone) VALUES ('John Doe', 'john@example.com', '0123456789');
INSERT INTO users (name, email, phone) VALUES ('Jane Mary', 'jane@example.com', '0123456788');

-- Insert animal type into the animal_type table
INSERT INTO animal_type (type) VALUES ('dog');
INSERT INTO animal_type (type) VALUES ('cat');
INSERT INTO animal_type (type) VALUES ('rabbit');
INSERT INTO animal_type (type) VALUES ('bird');
INSERT INTO animal_type (type) VALUES ('crocodile');

-- Insert pets into the pet table (without specifying the id, let AUTO_INCREMENT handle it)
INSERT INTO pets (name, age, description, status, location_id, owner_id, animal_type_id) VALUES ('Fido', 3, 'A cute dog', 'MISSING', 1, 1, 1);
INSERT INTO pets (name, age, description, status, location_id, owner_id, animal_type_id) VALUES ('Kitty', 1, 'A cute cat', 'FOUND', 2, 1, 2);

-- Insert posts into the post table
INSERT INTO posts (title, content, type, pet_id, owner_id) VALUES ('Lost Dog', 'Please help me find my dog', 'Missing', 1, 1);
INSERT INTO posts (title, content, type, pet_id, owner_id) VALUES ('Found Cat', 'I found a cat', 'Found', 2, 2)

