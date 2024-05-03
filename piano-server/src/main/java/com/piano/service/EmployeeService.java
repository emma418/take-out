package com.piano.service;

import com.piano.dto.EmployeeDTO;
import com.piano.dto.EmployeeLoginDTO;
import com.piano.entity.Employee;

public interface EmployeeService {

    /**
     * employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void addEmployee(EmployeeDTO employeeDTO);
}
