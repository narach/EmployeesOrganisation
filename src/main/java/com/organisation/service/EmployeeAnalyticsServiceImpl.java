package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.EmployeeAnalytics;

import java.util.*;

public class EmployeeAnalyticsServiceImpl implements EmployeeAnalyticsService {

    @Override
    public List<EmployeeAnalytics> findEmployeeReportingIssues(Map<Long, Employee> employeesMap, int reportingLengthFactor) {
        Map<Long, List<Long>> reportingChains = buildReportingChains(employeesMap);
        List<Employee> employees = employeesMap.values().stream().toList();

        return employees.stream()
            .map( employee -> {
                List<Long> reportingChain = reportingChains.get(employee.getId());
                int reportingChainOverflow = getChainOverflow(reportingChain, reportingLengthFactor);
                return new EmployeeAnalytics(employee, reportingChain, reportingChainOverflow);
            }).filter(employeeAnalytics -> employeeAnalytics.getReportingChainOverflow() > 0)
            .toList();
    }

    private int getChainOverflow(List<Long> reportingChain, int reportingLengthFactor) {
        if (reportingChain.size() > reportingLengthFactor) {
            return reportingChain.size() - reportingLengthFactor;
        } else {
            return 0;
        }
    }

    private Map<Long, List<Long>> buildReportingChains(Map<Long, Employee> employeesMap) {
        Map<Long, List<Long>> reportingChainsMap = new HashMap<>();
        for (Map.Entry<Long, Employee> entry : employeesMap.entrySet() ) {
            Long employeeId = entry.getKey();
            Employee employee = entry.getValue();
            List<Long> reportingChain = new ArrayList<>();
            // Set empty reporting chain for CEO and self-managed employees
            if (employee.getManagerId() == null || employee.getManagerId().equals(employeeId)) {
                reportingChainsMap.put(employee.getId(), reportingChain);
                continue;
            }

            Employee nextManager = employeesMap.get(employee.getManagerId());
            while( nextManager.getManagerId() != null) {
                reportingChain.add(nextManager.getId());
                if (reportingChainsMap.containsKey(nextManager.getId())) {
                    reportingChain.addAll(reportingChainsMap.get(nextManager.getId()));
                    break;
                }
                nextManager = employeesMap.get(nextManager.getManagerId());
            }

            reportingChainsMap.put(employeeId, reportingChain);
        }
        return reportingChainsMap;
    }

}
