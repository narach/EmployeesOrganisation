package com.organisation.entities;

public class Manager extends Employee {
    private double avgSubordinatesSalary;
    public double getAvgSubordinatesSalary() {
        return avgSubordinatesSalary;
    }

    public void setAvgSubordinatesSalary(double avgSubordinatesSalary) {
        this.avgSubordinatesSalary = avgSubordinatesSalary;
    }

    public Manager(Employee employee, double avgSubordinatesSalary) {
        super(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getManagerId());
        this.avgSubordinatesSalary = avgSubordinatesSalary;
    }
}
