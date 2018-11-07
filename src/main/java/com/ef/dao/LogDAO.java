package com.ef.dao;

import com.ef.entity.AuditLog;

public abstract interface LogDAO
{
    public abstract Long save(AuditLog paramAuditLog);

    public abstract void clearTable();
}
