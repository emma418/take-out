package com.piano.mapper;

import com.github.pagehelper.Page;
import com.piano.annotation.AutoFill;
import com.piano.dto.EmployeePageQueryDTO;
import com.piano.entity.Employee;
import com.piano.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * get employee by name
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into employee (full_name, username, password, phone, gender, create_time, update_time, create_user, update_user) " +
            "values(#{fullName}, #{username}, #{phone}, #{gender}, #{status}, #{createTime}, #{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);

    Page<Employee> employeeQuery(EmployeePageQueryDTO employeePageQueryDTO);
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee selectById(Integer id);
}
