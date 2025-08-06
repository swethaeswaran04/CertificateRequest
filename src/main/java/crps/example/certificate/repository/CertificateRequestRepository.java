package crps.example.certificate.repository;

import crps.example.certificate.model.CertificateRequest;
import crps.example.certificate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, Long> {
    List<CertificateRequest> findByStudent(User student);
}
