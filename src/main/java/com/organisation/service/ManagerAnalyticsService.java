package com.organisation.service;

import com.organisation.entities.Manager;
import com.organisation.entities.ManagerAnalytics;

import java.util.List;

public interface ManagerAnalyticsService {
    List<ManagerAnalytics> findManagersWithLowSalaries(List<Manager> managers, double lowSalaryFactor);
    List<ManagerAnalytics> findManagersWithHighSalaries(List<Manager> managers, double highSalaryFactor);
}
