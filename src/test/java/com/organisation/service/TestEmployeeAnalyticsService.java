package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.EmployeeAnalytics;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmployeeAnalyticsService {

    @Test
    void testFindEmployeesWithLongReportingChain() {
        Map<Long, Employee> employeesMap = new HashMap<>();
        employeesMap.put(101L, new Employee(101L, "John", "Smith", 150000, null));
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, 101L));
        employeesMap.put(103L, new Employee(103L, "Alex", "Johnson", 90000, 102L));
        employeesMap.put(104L, new Employee(104L, "Emily", "Brown", 70000, 103L));
        employeesMap.put(105L, new Employee(105L, "Daniel", "Davis", 60000, 104L));
        employeesMap.put(106L, new Employee(106L, "Sarah", "Miller", 30000, 105L));
        employeesMap.put(107L, new Employee(107L, "Chris", "Wilson", 40000, 105L));
        employeesMap.put(108L, new Employee(108L, "Anna", "Taylor", 28000, 107L));

        int reportingLengthFactor = 4;

        EmployeeAnalyticsService employeeService = new EmployeeAnalyticsServiceImpl();
        List<EmployeeAnalytics> employeeIssues = employeeService.findEmployeeReportingIssues(employeesMap, reportingLengthFactor);
        assertEquals(employeeIssues.size(), 1);
    }

    @Test
    void testEmployeeIsManagerForHimself() {
        Map<Long, Employee> employeesMap = new HashMap<>();
        employeesMap.put(101L, new Employee(101L, "John", "Smith", 150000, null));
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, 102L));
        EmployeeAnalyticsService employeeService = new EmployeeAnalyticsServiceImpl();

        int reportingLengthFactor = 4;

        List<EmployeeAnalytics> employeeIssues = employeeService.findEmployeeReportingIssues(employeesMap, reportingLengthFactor);
        assertEquals(employeeIssues.size(), 0);
    }

    @Test
    void testAnotherReportingLengthFactor() {
        Map<Long, Employee> employeesMap = new HashMap<>();
        employeesMap.put(101L, new Employee(101L, "John", "Smith", 150000, null));
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, 101L));
        employeesMap.put(103L, new Employee(103L, "Alex", "Johnson", 90000, 102L));
        employeesMap.put(104L, new Employee(104L, "Emily", "Brown", 70000, 103L));
        employeesMap.put(105L, new Employee(105L, "Daniel", "Davis", 60000, 104L));
        employeesMap.put(106L, new Employee(106L, "Sarah", "Miller", 30000, 105L));
        employeesMap.put(107L, new Employee(107L, "Chris", "Wilson", 40000, 105L));
        employeesMap.put(108L, new Employee(108L, "Anna", "Taylor", 28000, 107L));

        EmployeeAnalyticsService employeeService = new EmployeeAnalyticsServiceImpl();
        List<EmployeeAnalytics> employeeIssues = employeeService.findEmployeeReportingIssues(employeesMap, 2);
        assertEquals(employeeIssues.size(), 4);
    }

    @Test
    void testEmptyEmployeesListProcessing() {
        Map<Long, Employee> employeesMap = new HashMap<>();
        EmployeeAnalyticsService employeeService = new EmployeeAnalyticsServiceImpl();
        List<EmployeeAnalytics> employeeIssues = employeeService.findEmployeeReportingIssues(employeesMap, 2);
        assertEquals(employeeIssues.size(), 0);
    }

    @Test
    void testMultipleCeoStructureProcessing() {
        Map<Long, Employee> employeesMap = new HashMap<>();
        employeesMap.put(101L, new Employee(101L, "John", "Smith", 150000, null));
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, null));

        employeesMap.put(103L, new Employee(103L, "Alex", "Johnson", 90000, 101L));
        employeesMap.put(104L, new Employee(104L, "Emily", "Brown", 70000, 103L));
        employeesMap.put(105L, new Employee(105L, "Daniel", "Davis", 60000, 104L));
        employeesMap.put(106L, new Employee(106L, "Sarah", "Miller", 30000, 105L));

        employeesMap.put(107L, new Employee(107L, "Chris", "Wilson", 40000, 102L));
        employeesMap.put(108L, new Employee(108L, "Anna", "Taylor", 28000, 107L));
        employeesMap.put(109L, new Employee(109L, "Anna", "Taylor", 28000, 108L));
        employeesMap.put(110L, new Employee(110L, "Anna", "Taylor", 28000, 109L));

        EmployeeAnalyticsService employeeService = new EmployeeAnalyticsServiceImpl();
        List<EmployeeAnalytics> employeeIssues = employeeService.findEmployeeReportingIssues(employeesMap, 2);
        assertEquals(employeeIssues.size(), 2);
    }
}
