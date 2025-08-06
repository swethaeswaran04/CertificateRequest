package crps.example.certificate.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CertificateRequestDTO {
    @NotNull
    private Long studentId;

    @NotBlank
    private String courseName;

    @NotBlank
    private String courseId;

    @NotNull
    private LocalDate dateOfCompletion;

    private String specialInstructions;

    // Getters & Setters
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }
    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
