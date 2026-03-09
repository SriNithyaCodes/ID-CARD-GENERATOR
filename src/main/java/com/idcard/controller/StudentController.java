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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String UPLOAD_DIR = "C:/Users/Nithya/OneDrive/Desktop/2nd year term 3/JAVA PROJECT/ID CARD PHOTOS/";

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(org.springframework.web.multipart.MultipartHttpServletRequest request)
            throws Exception {
        System.out.println("Received registration request for: " + request.getParameter("fullName"));
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
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists())
                dir.mkdirs();

            String fileName = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, photo.getBytes());
            student.setPhotoPath(path.toString());
        }

        if (studentService.getStudentByRollNumber(student.getRollNumber()).isPresent()) {
            throw new RuntimeException(
                    "Roll number '" + student.getRollNumber() + "' is already registered to another student.");
        }

        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getStudentPhoto(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(s -> {
                    try {
                        byte[] photoBytes = Files.readAllBytes(Paths.get(s.getPhotoPath()));
                        return ResponseEntity.ok()
                                .contentType(MediaType.IMAGE_JPEG)
                                .body(photoBytes);
                    } catch (Exception e) {
                        return ResponseEntity.notFound().<byte[]>build();
                    }
                })
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
