package com.organisation.entities;

public class ReportingConfig {
    private double lowSalaryFactor;

    private double highSalaryFactor;

    private int reportingLength;

    public ReportingConfig(double lowSalaryFactor, double highSalaryFactor, int reportingLength) {
        this.lowSalaryFactor = lowSalaryFactor;
        this.highSalaryFactor = highSalaryFactor;
        this.reportingLength = reportingLength;
    }

    public double getLowSalaryFactor() {
        return lowSalaryFactor;
    }

    public void setLowSalaryFactor(double lowSalaryFactor) {
        this.lowSalaryFactor = lowSalaryFactor;
    }

    public double getHighSalaryFactor() {
        return highSalaryFactor;
    }

    public void setHighSalaryFactor(double highSalaryFactor) {
        this.highSalaryFactor = highSalaryFactor;
    }

    public int getReportingLength() {
        return reportingLength;
    }

    public void setReportingLength(int reportingLength) {
        this.reportingLength = reportingLength;
    }
}
