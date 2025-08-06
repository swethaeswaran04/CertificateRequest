package crps.example.certificate.service;

import crps.example.certificate.model.CertificateRequest;
import org.springframework.stereotype.Service;

@Service
public class PDFService {
    public String generateCertificate(CertificateRequest request) {
        // Placeholder for PDF generation logic
        return "/certificates/" + request.getId() + ".pdf";
    }
}
