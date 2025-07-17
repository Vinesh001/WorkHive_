package com.amdocs.whs.interfaces;

import java.util.List;

import com.amdocs.whs.bean.ProgressUpdate;

public interface ProgressUpdateDao {
	boolean addProgressUpdate(ProgressUpdate update);
    List<ProgressUpdate> getUpdatesByContract(int contractId);
}
