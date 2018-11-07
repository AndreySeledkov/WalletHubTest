1.
select ip from audit_log c
where c.date BETWEEN "2017-01-01.13:00:00" AND "2017-01-01.14:00:00"
GROUP BY ip
HAVING ip > 100;

2.

select * from audit_log c
where ip="192.168.228.188";
