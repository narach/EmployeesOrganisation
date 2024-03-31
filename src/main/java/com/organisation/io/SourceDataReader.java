package com.organisation.io;

import com.organisation.entities.Employee;
import com.organisation.exceptions.WrongFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SourceDataReader {
    private static final String DELIMITER = ",";
    private static final int ID = 0;
    private static final int FIRST_NAME = 1;
    private static final int LAST_NAME = 2;
    private static final int SALARY = 3;
    private static final int MANAGER_ID = 4;

    private static final int MIN_RECORD_LENGTH = 4;
    private static final int MAX_RECORD_LENGTH = 5;

    public Map<Long, Employee> readEmployees(String sourceDataPath) throws IOException, WrongFormatException {
        Map<Long, Employee> employees = new HashMap<>();

        InputStream inputStream = new FileInputStream(sourceDataPath);
        Scanner scanner = new Scanner(inputStream);
        scanner.nextLine();
        while (scanner.hasNext()) {
            String employeeRow = scanner.next();
            Employee employee = parseEmployee(employeeRow);
            employees.put(employee.getId(), employee);
        }

        return employees;
    }

    private Employee parseEmployee(String dataLine) throws WrongFormatException {
        String[] employeeValues = dataLine.split(DELIMITER);

        if (employeeValues.length < MIN_RECORD_LENGTH ) {
            throw new WrongFormatException(String.format("Record %s has not all fields defined!", dataLine));
        }
        if (employeeValues.length > MAX_RECORD_LENGTH) {
            throw new WrongFormatException(String.format("Record %s has unused data!", dataLine));
        }

        long employeeId;
        Long managerId;
        double salary;

        try {
            employeeId = Long.parseLong(employeeValues[ID]);
            managerId = employeeValues.length > MANAGER_ID ? Long.parseLong(employeeValues[MANAGER_ID]) : null;
            salary = Double.parseDouble(employeeValues[SALARY]);
            return new Employee(employeeId, employeeValues[FIRST_NAME], employeeValues[LAST_NAME],
                    salary, managerId);
        } catch (NumberFormatException e) {
            throw new WrongFormatException(String.format("Record %s has wrong data format!", dataLine));
        }
    }
}
