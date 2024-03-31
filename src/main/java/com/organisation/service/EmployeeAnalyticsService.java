package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.EmployeeAnalytics;

import java.util.List;
import java.util.Map;

public interface EmployeeAnalyticsService {
    List<EmployeeAnalytics> findEmployeeReportingIssues(Map<Long, Employee> employeesMap, int reportingLengthFactor);
}
