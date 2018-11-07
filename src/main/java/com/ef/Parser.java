package com.ef;

import com.ef.entity.AuditLog;
import com.ef.service.AuditLogService;
import com.ef.utils.DateFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Andr on 07.11.2018.
 */
public class Parser {

    private AuditLogService auditLogService;


    public static void main(java.lang.String[] args) {

        if (args.length != 3) {
            System.out.print("Not valid arguments");
        }
        Parser parser = new Parser();
        parser.init();

        LocalDateTime date = DateFormatter.getLogDate(args[0]);
        String duration = args[1];
        int threshold = Integer.parseInt(args[2]);

        List<AuditLog> auditLogs = parser.parse("log/access.log");

        LocalDateTime finishDate = duration.equals("hourly") ? date.plusHours(1) : date.plusHours(24);

        Map<String, List<AuditLog>> ips = auditLogs.stream().filter(item -> item.getDate().isAfter(date) && item.getDate().isBefore(finishDate)).
                collect(Collectors.groupingBy(AuditLog::getIp));

        System.out.println(" -- Result -- ");
        ips.entrySet().stream().filter(item -> item.getValue().size() > threshold)
                .forEach(item -> {
                    System.out.println(item.getValue().get(0).getIp());
                    item.getValue().forEach(log -> parser.save(log));
                });
    }

    private void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        auditLogService = context.getBean(AuditLogService.class);

        auditLogService.clearTable();
    }

    private java.util.List<com.ef.entity.AuditLog> parse(java.lang.String filePath) {

        List<AuditLog> auditLogs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filePath)))) {

            br.lines().forEach(item -> {
                String[] values = item.split("\\|");
                auditLogs.add(new AuditLog(values[1], values[2], values[3], values[4], DateFormatter.getLogDate(values[0])));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return auditLogs;
    }

    private void save(com.ef.entity.AuditLog auditLog) {
        auditLogService.save(auditLog);
    }
}
