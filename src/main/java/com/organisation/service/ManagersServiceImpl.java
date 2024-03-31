package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.Manager;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

public class ManagersServiceImpl implements ManagerService {

    private final Map<Long, Employee> employees;

    public ManagersServiceImpl(Map<Long, Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Manager> findManagers(Map<Long, Employee> employees) {
        return employees.values().stream()
            .filter(employee -> employee.getManagerId() != null)
            .collect(groupingBy(Employee::getManagerId, averagingDouble(Employee::getSalary)))
            .entrySet()
            .stream()
            .map(this::getManagerData)
            .toList();
    }

    private Manager getManagerData(Map.Entry<Long, Double> avgSalaryEntry) {
        Long managerId = avgSalaryEntry.getKey();
        double avgSubordinatesSalary = avgSalaryEntry.getValue();
        Employee employee = employees.get(managerId);
        return new Manager(employee, avgSubordinatesSalary);
    }
}
