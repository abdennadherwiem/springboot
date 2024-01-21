package soa.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import soa.repository.PaymentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;


    @Test
    void getAllPayments() {
        List<Payment> payments = Arrays.asList(new Payment(), new Payment());
        Mockito.when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assert result != null;
        assert result.size() == 2;
    }

    @Test
    void getPaymentById() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        Mockito.when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

        Payment result = paymentService.getPaymentById(paymentId);

        assert result != null;
        assert result.getPaymentId().equals(paymentId);
    }

    @Test
    void createPayment() {
        Payment payment = new Payment();
        Mockito.when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.createPayment(payment);

        assert result != null;
    }

    @Test
    void updatePayment() {
        Long paymentId = 1L;
        Payment existingPayment = new Payment();
        existingPayment.setPaymentId(paymentId);
        Mockito.when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(existingPayment));
        Mockito.when(paymentRepository.save(existingPayment)).thenReturn(existingPayment);

        Payment updatedPayment = new Payment();
        updatedPayment.setPaymentId(paymentId);
        Payment result = paymentService.updatePayment(updatedPayment);

        assert result != null;
        assert result.getPaymentId().equals(paymentId);
    }

    @Test
    void deletePayment() {
        // Calling the service method
        Long paymentId = 1L;
        paymentService.deletePayment(paymentId);

        // Verifying that the delete method of paymentRepository is called
        Mockito.verify(paymentRepository, Mockito.times(1)).deleteById(paymentId);
    }
}
