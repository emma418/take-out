package com.piano.service;

import com.piano.dto.EmployeeDTO;
import com.piano.dto.EmployeeLoginDTO;
import com.piano.dto.EmployeePageQueryDTO;
import com.piano.entity.Employee;
import com.piano.result.PageResult;

public interface EmployeeService {

    /**
     * employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void addEmployee(EmployeeDTO employeeDTO);

    PageResult employeeQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
