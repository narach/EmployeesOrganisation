package com.organisation.entities;

import java.util.List;
import java.util.Optional;

public class EmployeeAnalytics {

    private Employee employee;

    private List<Long> reportingChain;

    private int reportingChainOverflow;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Long> getReportingChain() {
        return reportingChain;
    }

    public void setReportingChain(List<Long> reportingChain) {
        this.reportingChain = reportingChain;
    }

    public Integer getReportingChainOverflow() {
        return reportingChainOverflow;
    }

    public void setReportingChainOverflow(int reportingChainOverflow) {
        this.reportingChainOverflow = reportingChainOverflow;
    }

    public EmployeeAnalytics(Employee employee, List<Long> reportingChain, int reportingChainOverflow) {
        this.employee = employee;
        this.reportingChain = reportingChain;
        this.reportingChainOverflow = reportingChainOverflow;
    }


    @Override
    public String toString() {
        return "EmployeeAnalytics{" +
                "Employee: " + employee +
                ", Reporting Chain: " + reportingChain +
                ", Reporting Chain Overflow: " + reportingChainOverflow +
                '}';
    }
}
