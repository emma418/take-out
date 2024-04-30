package com.piano.service.impl;

import com.piano.constant.MessageConstant;
import com.piano.constant.StatusConstant;
import com.piano.dto.EmployeeLoginDTO;
import com.piano.entity.Employee;
import com.piano.exception.AccountLockedException;
import com.piano.exception.AccountNotFoundException;
import com.piano.exception.PasswordErrorException;
import com.piano.mapper.EmployeeMapper;
import com.piano.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * employee login
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // get employee by name
        Employee employee = employeeMapper.getByUsername(username);

        // check if employee exist
        if (employee == null) {
            // throw exception if employee doesn't exist
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // compare password
        // TODO apply md5 encode later
        if (!password.equals(employee.getPassword())) {
            // throw exception when password is incorrect
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            // throw exception if the account is locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }

}
