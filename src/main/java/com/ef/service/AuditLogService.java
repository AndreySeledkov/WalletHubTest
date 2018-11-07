package com.ef.service;

import com.ef.entity.AuditLog;

public interface AuditLogService
{
    public Long save(AuditLog paramAuditLog);

    public void clearTable();
}
