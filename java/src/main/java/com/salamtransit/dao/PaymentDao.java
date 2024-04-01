package com.salamtransit.dao;

import com.salamtransit.model.Payment;
import java.util.List;

public interface PaymentDao {
    void addPayment(Payment payment);
    Payment getPaymentById(Long paymentId);
    List<Payment> getAllPayments();
    void updatePayment(Payment payment);
    void deletePayment(Long paymentId);
}
