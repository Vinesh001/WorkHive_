package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.Contract;
import java.util.List;

public interface ContractDao {
	boolean createContract(Contract contract);
	Contract getContractById(int contractId);
	boolean updateContractStatus(int contractId, String status);
	List<Contract> getContractsByClientId(int clientId);
	List<Contract> getContractsByFreelancerId(int freelancerId);

}
