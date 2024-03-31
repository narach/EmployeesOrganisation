package com.organisation.service;

import com.organisation.entities.Employee;
import com.organisation.entities.ImprovementsReport;
import com.organisation.entities.ReportingConfig;

import java.util.Map;

public interface ReportService {
    ImprovementsReport createImprovementsReport(Map<Long, Employee> employees, ReportingConfig config);
}
