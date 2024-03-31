package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.Manager;
import com.organisation.entities.ManagerAnalytics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestManagerAnalyticsService {

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
    void testFindManagersWithLowSalaries() {
        double lowSalaryFactor = 1.2;
        ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsServiceImpl();
        List<ManagerAnalytics> managerLowSalaryIssues =
                managerAnalyticsService.findManagersWithLowSalaries(managers, lowSalaryFactor);
        assertEquals(managerLowSalaryIssues.size(), 1);
        ManagerAnalytics managerIssue = managerLowSalaryIssues.getFirst();
        assertEquals(managerIssue.getManager().getId(), 104L);
        assertEquals(managerIssue.getRecommendedSalaryChange(), 2000);
    }

    @Test
    void testManagersWithHighSalaries() {
        double highSalaryFactor = 1.5;
        ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsServiceImpl();
        List<ManagerAnalytics> managerHighSalaryIssues =
                managerAnalyticsService.findManagersWithHighSalaries(managers, highSalaryFactor);
        assertEquals(managerHighSalaryIssues.size(), 1);
        ManagerAnalytics managerIssue = managerHighSalaryIssues.getFirst();
        assertEquals(managerIssue.getManager().getId(), 105L);
        assertEquals(managerIssue.getRecommendedSalaryChange(), -7500);
    }

    @Test
    void testLowSalariesWithCustomFactor() {
        double lowSalaryFactor = 1.5;
        ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsServiceImpl();
        List<ManagerAnalytics> managerLowSalaryIssues =
                managerAnalyticsService.findManagersWithLowSalaries(managers, lowSalaryFactor);
        assertEquals(managerLowSalaryIssues.size(), 5);
    }

    @Test
    void testHighSalariesWithCustomFactor() {
        double highSalaryFactor = 2;
        ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsServiceImpl();
        List<ManagerAnalytics> managerHighSalaryIssues =
                managerAnalyticsService.findManagersWithHighSalaries(managers, highSalaryFactor);
        assertTrue(managerHighSalaryIssues.isEmpty());
    }
}
