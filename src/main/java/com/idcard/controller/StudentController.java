package com.idcard.controller;

import com.idcard.entity.Student;
import com.idcard.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student createStudent(org.springframework.web.multipart.MultipartHttpServletRequest request)
            throws Exception {
        Student student = new Student();
        student.setFullName(request.getParameter("fullName"));
        student.setRollNumber(request.getParameter("rollNumber"));
        student.setCourse(request.getParameter("course"));
        student.setAcademicYear(request.getParameter("academicYear"));
        student.setAddress(request.getParameter("address"));
        student.setBloodGroup(request.getParameter("bloodGroup"));
        student.setEmergencyContact(request.getParameter("emergencyContact"));

        String dateStr = request.getParameter("issueDate");
        if (dateStr != null && !dateStr.isEmpty()) {
            student.setIssueDate(java.time.LocalDate.parse(dateStr));
        }

        MultipartFile photo = request.getFile("photo");
        if (photo != null && !photo.isEmpty()) {
            student.setPhotoData(photo.getBytes());
        }

        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getStudentPhoto(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(s -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(s.getPhotoData()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/barcode")
    public ResponseEntity<byte[]> getBarcode(@PathVariable Long id) throws Exception {
        Student student = studentService.getStudentById(id).orElseThrow();
        byte[] barcode = studentService.generateBarcode(student.getRollNumber(), 200, 50);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(barcode);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) throws Exception {
        byte[] pdf = studentService.generateIdCardPdf(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=id-card-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
