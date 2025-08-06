package crps.example.certificate.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String courseId;
    private LocalDate dateOfCompletion;
    private String specialInstructions;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String certificateUrl;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
}
