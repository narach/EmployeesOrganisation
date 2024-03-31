package com.organisation.entities;

public class ManagerAnalytics {
    private Manager manager;

    private double salaryFactor;

    private double recommendedSalaryChange;

    public ManagerAnalytics(Manager manager, double salaryFactor, double recommendedSalaryChange) {
        this.manager = manager;
        this.salaryFactor = salaryFactor;
        this.recommendedSalaryChange = recommendedSalaryChange;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public double getSalaryFactor() {
        return salaryFactor;
    }

    public void setSalaryFactor(double salaryFactor) {
        this.salaryFactor = salaryFactor;
    }

    public double getRecommendedSalaryChange() {
        return recommendedSalaryChange;
    }

    public void setRecommendedSalaryChange(double recommendedSalaryChange) {
        this.recommendedSalaryChange = recommendedSalaryChange;
    }

    @Override
    public String toString() {
        return String.format("First name: %s, Last name: %s, Subordinates average salary: %.0f, " +
                        "Salary factor: %.2f, Recommended salary change: %.0f", manager.getFirstName(), manager.getLastName(),
                manager.getAvgSubordinatesSalary(), salaryFactor, recommendedSalaryChange);
    }
}
