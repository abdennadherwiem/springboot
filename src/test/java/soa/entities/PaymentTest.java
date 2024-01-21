package soa.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PaymentTest {

    @InjectMocks
    private Payment payment;

    @Mock
    private Client mockClient;

    @Mock
    private List<Invoice> mockInvoices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        payment = new Payment();
        payment.setPaymentId(1L);
        payment.setAmount(100.0);
        payment.setPaymentDate(new Date());
        payment.setCardHolderName("John Doe");
        payment.setCreditCardNumber("1234567890123456");
        payment.setExpirationDate("12/23");
        payment.setCvv("123");
        payment.setClient(mockClient);
        payment.setInvoices(mockInvoices);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(Long.valueOf(1L), payment.getPaymentId());
        assertEquals(100.0, payment.getAmount(), 0.0);
        assertEquals("John Doe", payment.getCardHolderName());
        assertEquals("1234567890123456", payment.getCreditCardNumber());
        assertEquals("12/23", payment.getExpirationDate());
        assertEquals("123", payment.getCvv());
        assertEquals(mockClient, payment.getClient());
        assertEquals(mockInvoices, payment.getInvoices());
    }

    @Test
    void testToString() {
        String expectedToString = "Payment{paymentId=1, amount=100.0, paymentDate=" + payment.getPaymentDate()
                + ", cardHolderName='John Doe', creditCardNumber='1234567890123456', expirationDate='12/23', cvv='123', invoices=" + mockInvoices + '}';
        assertEquals(expectedToString, payment.toString());
    }

    @Test
    void testClientSetter() {
        Client newClient = new Client();
        payment.setClient(newClient);
        assertEquals(newClient, payment.getClient());
    }

    @Test
    void testInvoicesSetter() {
        List<Invoice> newInvoices = Arrays.asList(new Invoice(), new Invoice());
        payment.setInvoices(newInvoices);
        assertEquals(newInvoices, payment.getInvoices());
    }
}
