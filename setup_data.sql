CREATE DATABASE IF NOT EXISTS idcard_db;
USE idcard_db;

CREATE TABLE IF NOT EXISTS students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    roll_number VARCHAR(100) NOT NULL UNIQUE,
    course VARCHAR(150) NOT NULL,
    academic_year VARCHAR(50) NOT NULL,
    address TEXT NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    emergency_contact VARCHAR(20) NOT NULL,
    issue_date DATE NOT NULL,
    photo_path VARCHAR(255)
);

-- Insert Sample Data
INSERT INTO students (full_name, roll_number, course, academic_year, address, blood_group, emergency_contact, issue_date) VALUES
('Arjun Sharma', 'AU2024001', 'B.Tech CSE', '2024-2028', 'Flat 101, Surya Apartments, Uppal, Hyderabad', 'O+', '9876543210', '2024-09-01'),
('Priya Verma', 'AU2024002', 'B.Tech ECE', '2024-2028', 'H.No 12-34, Road No 5, Jubilee Hills, Hyderabad', 'A+', '9876543211', '2024-09-01'),
('Rohan Gupta', 'AU2024003', 'B.Tech CSE', '2024-2028', '45, Lakshmi Nagar, Secunderabad', 'B+', '9876543212', '2024-09-01'),
('Anjali Nair', 'AU2024004', 'B.Tech IT', '2024-2028', 'Plot 89, Green Valley Colony, Himayatnagar, Hyderabad', 'AB+', '9876543213', '2024-09-01'),
('Vikram Singh', 'AU2024005', 'B.Tech MECH', '2024-2028', 'Room 202, Boys Hostel, Aurora Campus, Hyderabad', 'O-', '9876543214', '2024-09-01'),
('Sneha Reddy', 'AU2024006', 'B.Tech CSE', '2024-2028', 'H.No 7-1, Near Metro Station, Tarnaka, Hyderabad', 'A-', '9876543215', '2024-09-01');

-- Query to view all registered students
SELECT * FROM students;

