package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.Payment;
import java.util.List;

public interface PaymentDao {
	boolean releasePayment(Payment payment);
	boolean addPayment(int milestoneId, double amount);
	List<Payment> getPaymentsByClient(int clientId);
	List<Payment> getPaymentsByFreelancer(int freelancerId);
	List<Payment> getPaymentsByContractId(int contractId);

}
