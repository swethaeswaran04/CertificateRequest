package crps.example.certificate.service;

import crps.example.certificate.model.CertificateRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(CertificateRequest request) {
        System.out.println("Notification: Request " + request.getId() +
                " is now " + request.getStatus());
    }
}
