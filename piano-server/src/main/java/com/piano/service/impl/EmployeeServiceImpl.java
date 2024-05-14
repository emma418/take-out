package com.piano.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.piano.constant.MessageConstant;
import com.piano.constant.PasswordConstant;
import com.piano.constant.StatusConstant;
import com.piano.context.BaseContext;
import com.piano.dto.EmployeeDTO;
import com.piano.dto.EmployeeLoginDTO;
import com.piano.dto.EmployeePageQueryDTO;
import com.piano.entity.Employee;
import com.piano.exception.AccountLockedException;
import com.piano.exception.AccountNotFoundException;
import com.piano.exception.PasswordErrorException;
import com.piano.mapper.EmployeeMapper;
import com.piano.result.PageResult;
import com.piano.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

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
        // apply md5 encode
        password = DigestUtils.md5DigestAsHex(password.getBytes());
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

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(PasswordConstant.DEFAULT_PASSWORD);
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        //
//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    @Override
    public PageResult employeeQuery(EmployeePageQueryDTO employeePageQueryDTO) {

        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.employeeQuery(employeePageQueryDTO);

        long total = page.getTotal();
        List<Employee> result = page.getResult();

        return new PageResult(total, result);
    }

    @Override
        public void updateStatus(Long id, Integer status) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();

        employeeMapper.update(employee);
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = employeeMapper.selectById(id);

        return employee;
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(BaseContext.getCurrentId());

        employeeMapper.update(employee);
    }

}
