package payment.co.zw.payment.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.co.zw.payment.integration.entity.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByMerchantReference(String merchantReference);

}