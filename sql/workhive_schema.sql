-- CREATE DATABASE workhive;
USE workhive;
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role ENUM('Client', 'Freelancer', 'Admin') NOT NULL,
    bio TEXT,
    skills TEXT,
    resume_link VARCHAR(255)
);

CREATE TABLE projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    budget DECIMAL(10, 2),
    deadline DATE,
    status ENUM('Open', 'Closed', 'In Progress', 'Completed') DEFAULT 'Open',
    FOREIGN KEY (client_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE bids (
    bid_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT,
    freelancer_id INT,
    proposal TEXT,
    bid_amount DECIMAL(10, 2),
    status ENUM('Pending', 'Accepted', 'Rejected') DEFAULT 'Pending',
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (freelancer_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT UNIQUE,
    client_id INT,
    freelancer_id INT,
    start_date DATE,
    end_date DATE,
    status ENUM('Active', 'Completed', 'Terminated') DEFAULT 'Active',
    FOREIGN KEY (project_id) REFERENCES projects(project_id),
    FOREIGN KEY (client_id) REFERENCES users(user_id),
    FOREIGN KEY (freelancer_id) REFERENCES users(user_id)
);

CREATE TABLE milestones (
    milestone_id INT AUTO_INCREMENT PRIMARY KEY,
    contract_id INT,
    description TEXT,
    due_date DATE,
    is_completed BOOLEAN DEFAULT FALSE,
    is_paid BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (contract_id) REFERENCES contracts(contract_id) ON DELETE CASCADE
);

CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    milestone_id INT UNIQUE,
    amount DECIMAL(10,2),
    payment_date DATE,
    status ENUM('Paid', 'Pending') DEFAULT 'Pending',
    FOREIGN KEY (milestone_id) REFERENCES milestones(milestone_id) ON DELETE CASCADE
);

SHOW TABLES;