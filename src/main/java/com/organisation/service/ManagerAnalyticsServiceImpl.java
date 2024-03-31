package com.organisation.service;

import com.organisation.entities.Manager;
import com.organisation.entities.ManagerAnalytics;

import java.util.List;

public class ManagerAnalyticsServiceImpl implements ManagerAnalyticsService {
    @Override
    public List<ManagerAnalytics> findManagersWithLowSalaries(List<Manager> managers, double lowSalaryFactor) {
        return managers.stream()
            .map(manager -> {
                double salaryFactor = manager.getSalary()/ manager.getAvgSubordinatesSalary();
                double recommendedSalaryChange = 0;
                if (salaryFactor < lowSalaryFactor) {
                    recommendedSalaryChange = manager.getAvgSubordinatesSalary()*lowSalaryFactor - manager.getSalary();
                }
                return new ManagerAnalytics(manager, salaryFactor, recommendedSalaryChange);
            })
            .filter(managerAnalytics -> managerAnalytics.getRecommendedSalaryChange() != 0)
            .toList();
    }

    @Override
    public List<ManagerAnalytics> findManagersWithHighSalaries(List<Manager> managers, double highSalaryFactor) {
        return managers.stream()
            .map(manager -> {
                double salaryFactor = manager.getSalary()/ manager.getAvgSubordinatesSalary();
                double recommendedSalaryChange = 0;
                if (salaryFactor > highSalaryFactor) {
                    recommendedSalaryChange = manager.getAvgSubordinatesSalary()*highSalaryFactor - manager.getSalary();
                }
                return new ManagerAnalytics(manager, salaryFactor, recommendedSalaryChange);
            })
            .filter(managerAnalytics -> managerAnalytics.getRecommendedSalaryChange() != 0)
            .toList();
    }
}
