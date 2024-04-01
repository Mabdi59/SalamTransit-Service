package com.salamtransit.controller;

import com.salamtransit.dao.PaymentDao;
import com.salamtransit.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentDao paymentDao;

    @Autowired
    public PaymentController(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @PostMapping
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        paymentDao.addPayment(payment);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        Payment payment = paymentDao.getPaymentById(paymentId);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentDao.getAllPayments();
        if (payments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(payments, HttpStatus.OK);
        }
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long paymentId, @RequestBody Payment payment) {
        Payment existingPayment = paymentDao.getPaymentById(paymentId);
        if (existingPayment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            payment.setPaymentId(paymentId); // Make sure we're updating the correct payment
            paymentDao.updatePayment(payment);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        Payment existingPayment = paymentDao.getPaymentById(paymentId);
        if (existingPayment != null) {
            paymentDao.deletePayment(paymentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
