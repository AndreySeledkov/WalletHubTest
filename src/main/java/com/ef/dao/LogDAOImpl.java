package com.ef.dao;

import com.ef.entity.AuditLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAOImpl
        extends AbstractDao<Long, AuditLog>
        implements LogDAO
{
    public Long save(AuditLog auditLog)
    {
        Session session = getSessionFactory().getCurrentSession();
        return (Long)session.save(auditLog);
    }

    public void clearTable()
    {
        String stringQuery = "DELETE FROM AuditLog";
        Query query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
        query.executeUpdate();
    }
}
