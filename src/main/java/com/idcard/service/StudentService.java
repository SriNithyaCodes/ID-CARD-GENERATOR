package com.idcard.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.idcard.entity.Student;
import com.idcard.repository.StudentRepository;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public byte[] generateBarcode(String text, int width, int height) throws Exception {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(text, BarcodeFormat.CODE_128, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public byte[] generateIdCardPdf(Long studentId) throws Exception {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        // Simple PDF Generation Logic - Front & Back (Mockup for now, real layout can be complex)
        document.add(new Paragraph("AURORA DEEMED TO BE UNIVERSITY").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(18));
        document.add(new Paragraph("School of Engineering").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        document.add(new Paragraph("\n"));
        
        document.add(new Paragraph("Name: " + student.getFullName()));
        document.add(new Paragraph("Roll No: " + student.getRollNumber()));
        document.add(new Paragraph("Course: " + student.getCourse()));
        document.add(new Paragraph("Academic Year: " + student.getAcademicYear()));
        
        // Add Barcode
        byte[] barcodeBytes = generateBarcode(student.getRollNumber(), 200, 50);
        Image barcodeImage = new Image(ImageDataFactory.create(barcodeBytes));
        document.add(barcodeImage.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER));

        document.add(new Paragraph("\n--- BACK SIDE ---").setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Address: " + student.getAddress()));
        document.add(new Paragraph("Blood Group: " + student.getBloodGroup()));
        document.add(new Paragraph("Emergency Contact: " + student.getEmergencyContact()));
        document.add(new Paragraph("Issue Date: " + student.getIssueDate()));

        document.close();
        return baos.toByteArray();
    }
}
