package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.Contract;
import java.util.List;

public interface ContractDao {
	boolean createContract(Contract contract);

	List<Contract> getContractsByClientId(int clientId);
	List<Contract> getContractsByFreelancerId(int freelancerId);
}
