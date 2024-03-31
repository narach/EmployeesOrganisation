package com.organisation.io;

import com.organisation.entities.ImprovementsReport;

public class ReportWriter {
    public void writeOrgReport(ImprovementsReport report) {
        System.out.println("Issues in organizational structure:");
        System.out.println("Managers with too low salaries:");
        report.getManagersWithLowSalaries().forEach(System.out::println);
        System.out.println("Managers with too high salaries:");
        report.getManagersWithHighSalaries().forEach(System.out::println);
        System.out.println("Employees with too long reporting lines:");
        report.getEmployeesWithLongReportingLines().forEach(System.out::println);
    }
}
