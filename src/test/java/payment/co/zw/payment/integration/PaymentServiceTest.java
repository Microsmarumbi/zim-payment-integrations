package payment.co.zw.payment.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import payment.co.zw.payment.integration.entity.Payment;
import payment.co.zw.payment.integration.entity.PaymentStatus;
import payment.co.zw.payment.integration.service.PaymentService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    void contextLoads() {
        assertNotNull(paymentService);
    }
    @Test
    void shouldCreatePaymentWithPendingStatus() {

        Payment payment = new Payment();

        payment.setProvider("PAYNOW");
        payment.setAmount(new BigDecimal("25.00"));
        payment.setCurrency("USD");

        Payment savedPayment = paymentService.createPayment(payment);

        assertNotNull(savedPayment.getId());

        assertNotNull(savedPayment.getMerchantReference());

        assertTrue(savedPayment.getMerchantReference().startsWith("PAY-"));

        assertEquals(PaymentStatus.PENDING, savedPayment.getStatus());
    }
}
