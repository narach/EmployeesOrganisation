package com.organisation.entities;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private double salary;
    private Long managerId;

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public Employee() {}

    public Employee(Long id, String firstName, String lastName, double salary, Long managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }



    @Override
    public String toString() {
        return String.format("ID: %d, First name: %s, Last name: %s, Salary: %.0f, Manager ID: %d", id, firstName, lastName, salary, managerId);
    }

}
