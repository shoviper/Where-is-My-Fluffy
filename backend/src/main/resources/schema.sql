-- -- Drop tables if they exist
-- DROP TABLE IF EXISTS `posts`;
-- DROP TABLE IF EXISTS `pets`;
-- DROP TABLE IF EXISTS `users`;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    location VARCHAR(100)
);

-- Create Pets Table
CREATE TABLE IF NOT EXISTS pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age FLOAT NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('MISSING', 'FOUND')),
    owner_id INT,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

-- Create Posts Table
CREATE TABLE IF NOT EXISTS posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    pet_id INT,
    owner_id INT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`pet_id`) REFERENCES `pets`(id) ON DELETE CASCADE,
    FOREIGN KEY (`owner_id`) REFERENCES `users`(id) ON DELETE CASCADE
);

-- Insert users into the user table (without specifying the id, let AUTO_INCREMENT handle it)
INSERT INTO users (name, email, phone, location) VALUES ('John Doe', 'john@example.com', '0123456789', 'bangkok');
INSERT INTO users (name, email, phone, location) VALUES ('Jane Mary', 'jane@example.com', '0123456788', 'thailand');

-- Insert pets into the pet table (without specifying the id, let AUTO_INCREMENT handle it)
INSERT INTO pets (name, age, description, status, owner_id) VALUES ('Tommy', 2, 'A cute dog', 'MISSING', 1);
INSERT INTO pets (name, age, description, status, owner_id) VALUES ('Kitty', 1, 'A cute cat', 'FOUND', 2);

-- Insert posts into the post table
INSERT INTO posts (title, content, pet_id, owner_id) VALUES ('Lost Dog', 'Please help me find my dog', 1, 1);
INSERT INTO posts (title, content, pet_id, owner_id) VALUES ('Found Cat', 'I found a cat', 2, 2);