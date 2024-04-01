package com.salamtransit.dao;

import com.salamtransit.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class JdbcPaymentDao implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Payment> rowMapper = (rs, rowNum) -> {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getLong("payment_id"));
        payment.setRideId(rs.getInt("ride_id"));
        payment.setUserId(rs.getInt("user_id"));
        payment.setAmount(rs.getBigDecimal("amount"));
        payment.setPaymentDate(rs.getTimestamp("payment_date"));
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setStatus(rs.getString("status"));
        return payment;
    };

    @Override
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payments (ride_id, user_id, amount, payment_date, payment_method, status) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, payment.getRideId(), payment.getUserId(), payment.getAmount(), payment.getPaymentDate(), payment.getPaymentMethod(), payment.getStatus());
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{paymentId}, rowMapper);
    }

    @Override
    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM payments";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void updatePayment(Payment payment) {
        String sql = "UPDATE payments SET ride_id = ?, user_id = ?, amount = ?, payment_date = ?, payment_method = ?, status = ? WHERE payment_id = ?";
        jdbcTemplate.update(sql, payment.getRideId(), payment.getUserId(), payment.getAmount(), payment.getPaymentDate(), payment.getPaymentMethod(), payment.getStatus(), payment.getPaymentId());
    }

    @Override
    public void deletePayment(Long paymentId) {
        String sql = "DELETE FROM payments WHERE payment_id = ?";
        jdbcTemplate.update(sql, paymentId);
    }
}
