package com.amdocs.whs.services;

import java.util.List;
import com.amdocs.whs.bean.Contract;
import com.amdocs.whs.dao.ContractDaoImpl;

public class ContractService {

    private final ContractDaoImpl contractDao;

    public ContractService() {
        this.contractDao = new ContractDaoImpl();
    }

    public boolean createContract(Contract contract) {
        if (contract.getProjectId() == 0 || contract.getClientId() == 0 || contract.getFreelancerId() == 0) {
            System.out.println("Contract info incomplete.");
            return false;
        }
        return contractDao.createContract(contract);
    }

    public List<Contract> getContractsByClient(int clientId) {
        return contractDao.getContractsByClientId(clientId);
    }

    public List<Contract> getContractsByFreelancerId(int freelancerId) {
        return contractDao.getContractsByFreelancerId(freelancerId);
    }
}
