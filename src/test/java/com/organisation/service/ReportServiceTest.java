package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.ImprovementsReport;
import com.organisation.entities.Manager;
import com.organisation.entities.ReportingConfig;
import com.organisation.io.ConfigReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportServiceTest {

    Map<Long, Employee> employeesMap = new HashMap<>();

    List<Manager> managers;

    @BeforeEach
    void initEmployeesList() {
        employeesMap.put(101L, new Employee(101L, "John", "Smith", 150000, null));
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, 101L));
        employeesMap.put(103L, new Employee(103L, "Alex", "Johnson", 90000, 102L));
        employeesMap.put(104L, new Employee(104L, "Emily", "Brown", 70000, 103L));
        employeesMap.put(105L, new Employee(105L, "Daniel", "Davis", 60000, 104L));
        employeesMap.put(106L, new Employee(106L, "Sarah", "Miller", 30000, 105L));
        employeesMap.put(107L, new Employee(107L, "Chris", "Wilson", 40000, 105L));
        employeesMap.put(108L, new Employee(108L, "Anna", "Taylor", 28000, 107L));

        ManagerService managerService = new ManagersServiceImpl(employeesMap);
        managers = managerService.findManagers(employeesMap);
    }

    @Test
    void testCreateImprovementsReportWithDefaultConfig() {
        ReportingConfig defaultConfig = ConfigReader.createDefaultConfig();
        ReportService reportService = new ReportServiceImpl();
        ImprovementsReport report = reportService.createImprovementsReport(employeesMap, defaultConfig);
        assertEquals(report.getManagersWithLowSalaries().size(), 1);
        assertEquals(report.getManagersWithLowSalaries().getFirst().getManager().getId(), 104L);
        assertEquals(report.getManagersWithHighSalaries().size(), 1);
        assertEquals(report.getManagersWithHighSalaries().getFirst().getManager().getId(), 105L);
        assertEquals(report.getEmployeesWithLongReportingLines().size(), 1);
        assertEquals(report.getEmployeesWithLongReportingLines().getFirst().getEmployee().getId(), 108L);
    }

    @Test
    void testCreateImprovementsReportWithCustomConfig() {
        ReportingConfig customConfig = new ReportingConfig(1.1, 1.2, 3);
        ReportService reportService = new ReportServiceImpl();
        ImprovementsReport report = reportService.createImprovementsReport(employeesMap, customConfig);
        assertTrue(report.getManagersWithLowSalaries().isEmpty());
        assertEquals(report.getManagersWithHighSalaries().size(), 5);
        assertEquals(report.getEmployeesWithLongReportingLines().size(), 3);
    }
}
