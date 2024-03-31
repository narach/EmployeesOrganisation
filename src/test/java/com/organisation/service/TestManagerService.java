package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.Manager;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestManagerService {

    Map<Long, Employee> employeesMap;

    ManagerService managerService;

    @Test
    void testFindManagers() {
        employeesMap = new HashMap<>();
        employeesMap.put(101L, new Employee(101L, "John", "Smith", 150000, null));
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, 101L));
        employeesMap.put(103L, new Employee(103L, "Alex", "Johnson", 90000, 102L));
        employeesMap.put(104L, new Employee(104L, "Emily", "Brown", 70000, 103L));
        employeesMap.put(105L, new Employee(105L, "Daniel", "Davis", 60000, 104L));
        employeesMap.put(106L, new Employee(106L, "Sarah", "Miller", 30000, 105L));
        employeesMap.put(107L, new Employee(107L, "Chris", "Wilson", 40000, 105L));
        employeesMap.put(108L, new Employee(108L, "Anna", "Taylor", 28000, 107L));

        managerService = new ManagersServiceImpl(employeesMap);
        List<Manager> managers = managerService.findManagers(employeesMap);
        assertEquals(managers.size(), 6);
    }

    @Test
    void testFindManagersFlat() {
        employeesMap = new HashMap<>();
        employeesMap.put(101L, new Employee(101L, "John", "Smith", 150000, null));
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, 101L));
        employeesMap.put(103L, new Employee(103L, "Alex", "Johnson", 90000, 101L));
        employeesMap.put(104L, new Employee(104L, "Emily", "Brown", 70000, 101L));

        managerService = new ManagersServiceImpl(employeesMap);
        List<Manager> managers = managerService.findManagers(employeesMap);
        assertEquals(managers.size(), 1);
    }

    @Test
    void testFindManagersAllSelf() {
        employeesMap = new HashMap<>();
        employeesMap.put(102L, new Employee(102L, "Jane", "Doe", 120000, 102L));
        employeesMap.put(103L, new Employee(103L, "Alex", "Johnson", 90000, 103L));
        employeesMap.put(104L, new Employee(104L, "Emily", "Brown", 70000, 104L));

        managerService = new ManagersServiceImpl(employeesMap);
        List<Manager> managers = managerService.findManagers(employeesMap);
        assertEquals(managers.size(), 3);
    }

    @Test
    void testFindManagersCyclic() {
        employeesMap = new HashMap<>();
        employeesMap.put(101L, new Employee(102L, "Jane", "Doe", 120000, 102L));
        employeesMap.put(102L, new Employee(103L, "Alex", "Johnson", 90000, 101L));

        managerService = new ManagersServiceImpl(employeesMap);
        List<Manager> managers = managerService.findManagers(employeesMap);
        assertEquals(managers.size(), 2);
    }
}
