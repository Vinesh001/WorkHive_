package com.amdocs.whs.services;

import java.time.LocalDate;
import java.util.List;

import com.amdocs.whs.bean.Milestone;
import com.amdocs.whs.bean.Payment;
import com.amdocs.whs.dao.MilestoneDaoImpl;
import com.amdocs.whs.dao.PaymentDaoImpl;
import com.amdocs.whs.interfaces.MilestoneDao;

public class PaymentService {

    private final PaymentDaoImpl paymentDao;
    private final MilestoneDao milestoneDao = new MilestoneDaoImpl();

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
    
    public boolean recordPayment(int milestoneId, double amount) {
        return paymentDao.addPayment(milestoneId, amount);
    }
    
    public boolean recordPaymentForContract(int contractId, double amount) {
//        String sql = "INSERT INTO payments (milestone_id, amount, payment_date, status) VALUES (?, ?, CURRENT_DATE, 'Paid')";
        
        int dummyMilestoneId = getOrCreateDummyMilestoneForContract(contractId, amount);
        
        return paymentDao.addPayment(dummyMilestoneId, amount);
    }
    
    private int getOrCreateDummyMilestoneForContract(int contractId, double amount) {

        List<Milestone> milestones = milestoneDao.getMilestonesByContractId(contractId);
        for (Milestone m : milestones) {
            if (m.getDescription().equalsIgnoreCase("Final Payment")) {
                return m.getMilestoneId();
            }
        }

        Milestone m = new Milestone();
        m.setContractId(contractId);
        m.setDescription("Final Payment");
        m.setDueDate(LocalDate.now().toString());
        m.setCompleted(true);
        m.setPaid(false);

        int milestoneId = milestoneDao.createMilestoneAndReturnId(m);
        return milestoneId;
    }
    
    public List<Payment> getPaymentsByContractId(int contractId) {
    	return paymentDao.getPaymentsByContractId(contractId);
    }

}
