package com.organisation.entities;

import java.util.List;

public class ImprovementsReport {
    private List<ManagerAnalytics> managersWithLowSalaries;
    private List<ManagerAnalytics> managersWithHighSalaries;
    private List<EmployeeAnalytics> employeesWithLongReportingLines;

    public ImprovementsReport(List<ManagerAnalytics> managersWithLowSalaries, List<ManagerAnalytics> managersWithHighSalaries,
                              List<EmployeeAnalytics> employeesWithLongReportingLines) {
        this.managersWithLowSalaries = managersWithLowSalaries;
        this.managersWithHighSalaries = managersWithHighSalaries;
        this.employeesWithLongReportingLines = employeesWithLongReportingLines;
    }

    public List<EmployeeAnalytics> getEmployeesWithLongReportingLines() {
        return employeesWithLongReportingLines;
    }

    public void setEmployeesWithLongReportingLines(List<EmployeeAnalytics> employeesWithLongReportingLines) {
        this.employeesWithLongReportingLines = employeesWithLongReportingLines;
    }

    public List<ManagerAnalytics> getManagersWithLowSalaries() {
        return managersWithLowSalaries;
    }

    public void setManagersWithLowSalaries(List<ManagerAnalytics> managersWithLowSalaries) {
        this.managersWithLowSalaries = managersWithLowSalaries;
    }

    public List<ManagerAnalytics> getManagersWithHighSalaries() {
        return managersWithHighSalaries;
    }

    public void setManagersWithHighSalaries(List<ManagerAnalytics> managersWithHighSalaries) {
        this.managersWithHighSalaries = managersWithHighSalaries;
    }
}
