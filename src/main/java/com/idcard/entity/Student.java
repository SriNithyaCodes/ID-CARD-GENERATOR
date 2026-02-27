package com.idcard.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "roll_number", nullable = false, unique = true)
    private String rollNumber;

    @Column(nullable = false)
    private String course;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "blood_group", nullable = false)
    private String bloodGroup;

    @Column(name = "emergency_contact", nullable = false)
    private String emergencyContact;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "photo_path")
    private String photoPath;

    // ===== CONSTRUCTORS =====

    public Student() {}

    public Student(Long id, String fullName, String rollNumber, String course,
                   String academicYear, String address, String bloodGroup,
                   String emergencyContact, LocalDate issueDate, String photoPath) {
        this.id = id;
        this.fullName = fullName;
        this.rollNumber = rollNumber;
        this.course = course;
        this.academicYear = academicYear;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.emergencyContact = emergencyContact;
        this.issueDate = issueDate;
        this.photoPath = photoPath;
    }

    // ===== GETTERS AND SETTERS =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
}