CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

INSERT INTO students (name, email) VALUES
('Aarav Mehta', 'aarav.mehta@example.com'),
('Niharika Patel', 'niharika.patel@example.com'),
('Riya Sharma', 'riya.sharma@example.com'),
('Karan Joshi', 'karan.joshi@example.com'),
('Sneha Iyer', 'sneha.iyer@example.com');


CREATE TABLE professors (
    professor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

INSERT INTO professors (name, email) VALUES
('Dr. Rajiv Nair', 'rajiv.nair@university.edu'),
('Prof. Meenakshi Rao', 'meenakshi.rao@university.edu'),
('Dr. Arjun Verma', 'arjun.verma@university.edu');


CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100),
    level ENUM('BEGINNER', 'INTERMEDIATE', 'ADVANCED'),
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES professors(professor_id)
);

INSERT INTO courses (course_name, level, professor_id) VALUES
('Introduction to Java', 'BEGINNER', 1),
('Database Systems', 'INTERMEDIATE', 2),
('Advanced Data Structures', 'ADVANCED', 3),
('Operating Systems', 'INTERMEDIATE', 1),
('Web Development', 'BEGINNER', 2);

CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    grade INT CHECK (grade BETWEEN 0 AND 100),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

INSERT INTO enrollments (student_id, course_id, grade) VALUES
(1, 1, 85),  -- Aarav in Introduction to Java
(1, 2, 78),  -- Aarav in Database Systems
(2, 1, 92),  -- Niharika in Introduction to Java
(2, 3, 88),  -- Niharika in Advanced Data Structures
(3, 2, 74),  -- Riya in Database Systems
(4, 4, 69),  -- Karan in Operating Systems
(5, 5, 95);  -- Sneha in Web Development






