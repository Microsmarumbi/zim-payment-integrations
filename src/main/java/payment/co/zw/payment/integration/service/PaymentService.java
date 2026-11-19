package payment.co.zw.payment.integration.service;

import org.springframework.stereotype.Service;
import payment.co.zw.payment.integration.entity.Payment;
import payment.co.zw.payment.integration.entity.PaymentStatus;
import payment.co.zw.payment.integration.repository.PaymentRepository;

import java.util.Locale;
import java.util.UUID;

@Service
public class PaymentService {


    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private String generateMerchantReference() {
        return "PAY-" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .toUpperCase(Locale.ROOT);
    }

    public Payment createPayment(Payment payment) {

        payment.setMerchantReference(generateMerchantReference());
        payment.setStatus(PaymentStatus.PENDING);

        return paymentRepository.save(payment);
    }
}
