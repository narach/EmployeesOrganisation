package com.organisation.service;

import com.organisation.entities.*;

import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

    @Override
    public ImprovementsReport createImprovementsReport(Map<Long, Employee> employees, ReportingConfig config) {
        ManagerService managerService = new ManagersServiceImpl(employees);
        List<Manager> managers = managerService.findManagers(employees);
        ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsServiceImpl();
        List<ManagerAnalytics> lowSalaryIssues = managerAnalyticsService.findManagersWithLowSalaries(managers, config.getLowSalaryFactor());
        List<ManagerAnalytics> highSalaryIssues = managerAnalyticsService.findManagersWithHighSalaries(managers, config.getHighSalaryFactor());
        EmployeeAnalyticsService employeeAnalyticsService = new EmployeeAnalyticsServiceImpl();
        List<EmployeeAnalytics> employeeReportingIssues = employeeAnalyticsService.findEmployeeReportingIssues(employees, config.getReportingLength());

        return new ImprovementsReport(lowSalaryIssues, highSalaryIssues, employeeReportingIssues);
    }
}
