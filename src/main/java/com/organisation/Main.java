package com.organisation;

import com.organisation.entities.Employee;
import com.organisation.entities.ImprovementsReport;
import com.organisation.entities.ReportingConfig;
import com.organisation.exceptions.WrongFormatException;
import com.organisation.io.ConfigReader;
import com.organisation.io.ReportWriter;
import com.organisation.io.SourceDataReader;
import com.organisation.service.ReportServiceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class Main {

    private static ReportingConfig readConfig(String configPath) {
        ConfigReader configReader = new ConfigReader();
        ReportingConfig config;
        try {
            config = configReader.readConfig(configPath);
        } catch (IOException e) {
            System.out.println("Config file is undefined or has wrong format");
            config = ConfigReader.createDefaultConfig();
        }
        return config;
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.err.println("File with organisation structure data should be provided as a first argument!");
            System.exit(0);
        }
        String sourcePath = args[0];
        ReportingConfig config;
        if (args.length > 1) {
            config = readConfig(args[1]);
        } else {
            config = ConfigReader.createDefaultConfig();
        }

        SourceDataReader sourceReader = new SourceDataReader();
        try {
            Map<Long, Employee> employeeMap = sourceReader.readEmployees(sourcePath);
            ReportServiceImpl reportService = new ReportServiceImpl();
            ImprovementsReport improvementsReport = reportService.createImprovementsReport(employeeMap, config);
            ReportWriter reportWriter = new ReportWriter();
            reportWriter.writeOrgReport(improvementsReport);

        } catch (IOException e) {
            System.out.println(e);
        } catch (WrongFormatException wfe) {
            System.err.println(wfe.getMessage());
            System.err.println("Skip further processing!");
        }
    }
}