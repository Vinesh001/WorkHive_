package com.amdocs.whs.services;

import java.util.List;
import com.amdocs.whs.bean.ProgressUpdate;
import com.amdocs.whs.dao.ProgressUpdateDaoImpl;
import com.amdocs.whs.interfaces.ProgressUpdateDao;

public class ProgressUpdateService {
    private final ProgressUpdateDao dao = new ProgressUpdateDaoImpl();

    public boolean submitUpdate(ProgressUpdate update) {
        return dao.addProgressUpdate(update);
    }

    public List<ProgressUpdate> getUpdates(int contractId) {
        return dao.getUpdatesByContract(contractId);
    }
}
