package com.organisation.io;

import com.organisation.entities.ReportingConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final double DEFAULT_LOW_SALARY_FACTOR = 1.2;

    private static final double DEFAULT_HIGH_SALARY_FACTOR = 1.5;

    private static final int DEFAULT_REPORTING_LENGTH_FACTOR = 4;
    public ReportingConfig readConfig(String configPath) throws IOException {
        InputStream inputStream = new FileInputStream(configPath);
        Properties props = new Properties();
        props.load(inputStream);

        return new ReportingConfig(
                Double.parseDouble(props.getProperty("lowSalaryFactor")),
                Double.parseDouble(props.getProperty("highSalaryFactor")),
                Integer.parseInt(props.getProperty("reportingLengthFactor"))
        );
    }

    public static ReportingConfig createDefaultConfig() {
        return new ReportingConfig(DEFAULT_LOW_SALARY_FACTOR, DEFAULT_HIGH_SALARY_FACTOR,
                DEFAULT_REPORTING_LENGTH_FACTOR);
    }
}
