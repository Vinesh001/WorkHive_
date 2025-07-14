package com.amdocs.whs.services;

import java.util.List;
import com.amdocs.whs.bean.Payment;
import com.amdocs.whs.dao.PaymentDaoImpl;

public class PaymentService {

    private final PaymentDaoImpl paymentDao;

    public PaymentService() {
        this.paymentDao = new PaymentDaoImpl();
    }

    public boolean releasePayment(Payment p) {
        return paymentDao.releasePayment(p);
    }

    public List<Payment> getClientPayments(int clientId) {
        return paymentDao.getPaymentsByClient(clientId);
    }

    public List<Payment> getFreelancerPayments(int freelancerId) {
        return paymentDao.getPaymentsByFreelancer(freelancerId);
    }
}
