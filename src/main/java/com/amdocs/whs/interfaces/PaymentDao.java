package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.Payment;
import java.util.List;

public interface PaymentDao {
	boolean releasePayment(Payment payment);

	List<Payment> getPaymentsByClient(int clientId);

	List<Payment> getPaymentsByFreelancer(int freelancerId);
}
