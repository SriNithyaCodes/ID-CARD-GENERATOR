CREATE DATABASE IF NOT EXISTS aurora_id_db;
USE aurora_id_db;

CREATE TABLE IF NOT EXISTS students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    roll_number VARCHAR(50) NOT NULL UNIQUE,
    course VARCHAR(100) NOT NULL,
    academic_year VARCHAR(50) NOT NULL,
    address TEXT NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    emergency_contact VARCHAR(20) NOT NULL,
    issue_date DATE NOT NULL,
    photo_path VARCHAR(255)
);

INSERT INTO students (full_name, roll_number, course, academic_year, address, blood_group, emergency_contact, issue_date) VALUES
('Arjun Sharma', 'AU2024001', 'B.Tech CSE', '2024-2028', 'Flat 101, Surya Apartments, Uppal, Hyderabad', 'O+', '9876543210', '2024-09-01'),
('Priya Verma', 'AU2024002', 'B.Tech ECE', '2024-2028', 'H.No 12-34, Road No 5, Jubilee Hills, Hyderabad', 'A+', '9876543211', '2024-09-01'),
('Rohan Gupta', 'AU2024003', 'B.Tech CSE', '2024-2028', '45, Lakshmi Nagar, Secunderabad', 'B+', '9876543212', '2024-09-01'),
('Anjali Nair', 'AU2024004', 'B.Tech IT', '2024-2028', 'Plot 89, Green Valley Colony, Himayatnagar, Hyderabad', 'AB+', '9876543213', '2024-09-01'),
('Vikram Singh', 'AU2024005', 'B.Tech MECH', '2024-2028', 'Room 202, Boys Hostel, Aurora Campus, Hyderabad', 'O-', '9876543214', '2024-09-01'),
('Sneha Reddy', 'AU2024006', 'B.Tech CSE', '2024-2028', 'H.No 7-1, Near Metro Station, Tarnaka, Hyderabad', 'A-', '9876543215', '2024-09-01'),
('Aditya Joshi', 'AU2024007', 'B.Tech EEE', '2024-2028', 'Villa 12, Palam Tree County, Gachibowli, Hyderabad', 'B-', '9876543216', '2024-09-01'),
('Meera Iyer', 'AU2024008', 'B.Tech CSE', '2024-2028', 'Flat 405, Pearl Heights, Banjara Hills, Hyderabad', 'O+', '9876543217', '2024-09-01'),
('Siddharth Malhotra', 'AU2024009', 'B.Tech CIVIL', '2024-2028', '23, Sai Enclave, Kondapur, Hyderabad', 'A+', '9876543218', '2024-09-01'),
('Kavya Rao', 'AU2024010', 'B.Tech ECE', '2024-2028', 'H.No 4-56/A, Pragathi Nagar, Kukatpally, Hyderabad', 'B+', '9876543219', '2024-09-01'),
('Ishaan Kapoor', 'AU2024011', 'B.Tech CSE', '2024-2028', 'Flat 10, Regency Park, Madhapur, Hyderabad', 'AB-', '9876543220', '2024-09-02'),
('Tanvi Deshmukh', 'AU2024012', 'B.Tech IT', '2024-2028', 'H.No 99, Shanti Nagar, Malakpet, Hyderabad', 'O+', '9876543221', '2024-09-02'),
('Aryan Saxena', 'AU2024013', 'B.Tech MECH', '2024-2028', 'Sector 4, Phase 2, Miyapur, Hyderabad', 'A+', '9876543222', '2024-09-02'),
('Riya Pandey', 'AU2024014', 'B.Tech CSE', '2024-2028', 'Flat 302, Galaxy residency, Manikonda, Hyderabad', 'B+', '9876543223', '2024-09-02'),
('Varun Teja', 'AU2024015', 'B.Tech EEE', '2024-2028', 'H.No 1-22, Peerzadiguda, Uppal, Hyderabad', 'O-', '9876543224', '2024-09-02'),
('Sanya Mirza', 'AU2024016', 'B.Tech CSE', '2024-2028', 'Flat 501, Royal Palms, Abids, Hyderabad', 'A-', '9876543225', '2024-09-02'),
('Kabir Khan', 'AU2024017', 'B.Tech CIVIL', '2024-2028', 'H.No 8-3-100, Yousufguda, Hyderabad', 'B-', '9876543226', '2024-09-02'),
('Diya Menon', 'AU2024018', 'B.Tech CSE', '2024-2028', 'Villa 45, Green Meadows, Tellapur, Hyderabad', 'O+', '9876543227', '2024-09-02'),
('Abhishek Pillai', 'AU2024019', 'B.Tech ECE', '2024-2028', 'H.No 15, Lake View Colony, Sainikpuri, Hyderabad', 'A+', '9876543228', '2024-09-02'),
('Nisha Choudhary', 'AU2024020', 'B.Tech IT', '2024-2028', 'Flat 208, Orchid Manor, Ramanthapur, Hyderabad', 'B+', '9876543229', '2024-09-02'),
('Rahul Bose', 'AU2024021', 'B.Tech CSE', '2024-2028', 'H.No 44/1, New Nallakunta, Hyderabad', 'AB+', '9876543230', '2024-09-03'),
('Ayesha Siddiqui', 'AU2024022', 'B.Tech MECH', '2024-2028', 'H.No 12-2-456, Mehdipatnam, Hyderabad', 'O+', '9876543231', '2024-09-03'),
('Manish Kumar', 'AU2024023', 'B.Tech EEE', '2024-2028', 'Flat 603, Silver Oaks, Nizampet, Hyderabad', 'A+', '9876543232', '2024-09-03'),
('Shreya Ghoshal', 'AU2024024', 'B.Tech CSE', '2024-2028', 'H.No 9-10/B, Nacharam, Hyderabad', 'B+', '9876543233', '2024-09-03'),
('Kartik Aaryan', 'AU2024025', 'B.Tech CIVIL', '2024-2028', 'H.No 2-33, Boduppal, Hyderabad', 'O-', '9876543234', '2024-09-03'),
('Janhavi Kapoor', 'AU2024026', 'B.Tech IT', '2024-2028', 'Flat 110, Lotus Apartments, Begumpet, Hyderabad', 'A-', '9876543235', '2024-09-03'),
('Ranbir Kapoor', 'AU2024027', 'B.Tech ECE', '2024-2028', 'Villa 8, Beverly Hills, Madhapur, Hyderabad', 'B-', '9876543236', '2024-09-03'),
('Alia Bhatt', 'AU2024028', 'B.Tech CSE', '2024-2028', 'Flat 402, Sea Breeze, Somajiguda, Hyderabad', 'O+', '9876543237', '2024-09-03'),
('Deepika Padukone', 'AU2024029', 'B.Tech CIVIL', '2024-2028', 'H.No 5/A, Park Lane, Secunderabad', 'A+', '9876543238', '2024-09-03'),
('Ranveer Singh', 'AU2024030', 'B.Tech CSE', '2024-2028', 'Flat 901, High Rise Towers, Gachibowli, Hyderabad', 'B+', '9876543239', '2024-09-03');
