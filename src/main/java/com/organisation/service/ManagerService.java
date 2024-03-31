package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<Manager> findManagers(Map<Long, Employee> employees);
}
