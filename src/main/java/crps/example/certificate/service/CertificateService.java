package crps.example.certificate.service;

import crps.example.certificate.model.*;
import crps.example.certificate.repository.CertificateRequestRepository;
import crps.example.certificate.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;
import java.util.List;

@Service
public class CertificateService {
    private final CertificateRequestRepository certRepo;
    private final UserRepository userRepo;
    private final PDFService pdfService;
    private final NotificationService notificationService;

    public CertificateService(CertificateRequestRepository certRepo, UserRepository userRepo,
                               PDFService pdfService, NotificationService notificationService) {
        this.certRepo = certRepo;
        this.userRepo = userRepo;
        this.pdfService = pdfService;
        this.notificationService = notificationService;
    }

    public CertificateRequest submitRequest(Long studentId, String courseName, String courseId,
                                            LocalDate date, String instructions) {
        User student = userRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        CertificateRequest request = CertificateRequest.builder()
                .courseName(courseName)
                .courseId(courseId)
                .dateOfCompletion(date)
                .specialInstructions(instructions)
                .status(RequestStatus.PENDING)
                .student(student)
                .build();
        return certRepo.save(request);
    }

    public List<CertificateRequest> getRequestsForStudent(Long studentId) {
        User student = userRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        return certRepo.findByStudent(student);
    }

    public List<CertificateRequest> getAllRequests() {
        return certRepo.findAll();
    }

    public CertificateRequest approveRequest(Long requestId) {
        CertificateRequest req = certRepo.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate request not found"));

        req.setStatus(RequestStatus.APPROVED);
        String pdfUrl = pdfService.generateCertificate(req);
        req.setCertificateUrl(pdfUrl);
        notificationService.sendNotification(req);
        return certRepo.save(req);
    }

    public CertificateRequest rejectRequest(Long requestId) {
        CertificateRequest req = certRepo.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate request not found"));

        req.setStatus(RequestStatus.REJECTED);
        notificationService.sendNotification(req);
        return certRepo.save(req);
    }

    public void deleteRequest(Long id) {
        certRepo.deleteById(id);
    }
}
