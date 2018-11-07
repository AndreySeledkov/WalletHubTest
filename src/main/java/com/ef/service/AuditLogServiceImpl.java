package com.ef.service;

import com.ef.dao.LogDAO;
import com.ef.entity.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuditLogServiceImpl
        implements AuditLogService
{
    @Autowired
    private LogDAO logDAO;

    public Long save(AuditLog auditLog)
    {
        return this.logDAO.save(auditLog);
    }

    public void clearTable()
    {
        this.logDAO.clearTable();
    }
}
