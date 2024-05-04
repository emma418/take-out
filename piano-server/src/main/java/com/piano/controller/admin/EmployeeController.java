package com.piano.controller.admin;

import com.piano.constant.JwtClaimsConstant;
import com.piano.dto.EmployeeDTO;
import com.piano.dto.EmployeeLoginDTO;
import com.piano.dto.EmployeePageQueryDTO;
import com.piano.entity.Employee;
import com.piano.properties.JwtProperties;
import com.piano.result.PageResult;
import com.piano.result.Result;
import com.piano.service.EmployeeService;
import com.piano.utils.JwtUtil;
import com.piano.vo.EmployeeLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * employee management
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("employee login：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        // generate jwt token after login
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .fullName(employee.getFullName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * logout
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping
    @ApiOperation("add a new employee")
    public Result addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("add a new employee: {}", employeeDTO);

        employeeService.addEmployee(employeeDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(("employee query"))
    public Result<PageResult> employeeQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("employee query parameters: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.employeeQuery(employeePageQueryDTO);

        return Result.success(pageResult);
    }

}
