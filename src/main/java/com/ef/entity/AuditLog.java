package com.ef.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUDIT_LOG")
public class AuditLog
        implements Serializable
{
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name="ID_AUDIT_LOG", unique=true)
    private long id;
    @Basic
    @Column(name="IP")
    private String ip;
    @Basic
    @Column(name="REQUEST")
    private String request;
    @Basic
    @Column(name="STATUS")
    private String status;
    @Basic
    @Column(name="USER_AGENT")
    private String userAgent;
    @Basic
    @Column(name="DATE")
    private LocalDateTime date;

    public AuditLog() {}

    public AuditLog(String ip, String request, String status, String userAgent, LocalDateTime date)
    {
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
        this.date = date;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getIp()
    {
        return this.ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getRequest()
    {
        return this.request;
    }

    public void setRequest(String request)
    {
        this.request = request;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getUserAgent()
    {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public LocalDateTime getDate()
    {
        return this.date;
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }

    public String toString()
    {
        return "AuditLog{id=" + this.id + ", ip='" + this.ip + '\'' + ", request='" + this.request + '\'' + ", status='" + this.status + '\'' + ", userAgent='" + this.userAgent + '\'' + ", date=" + this.date + '}';
    }
}
