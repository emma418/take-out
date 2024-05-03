package com.piano.mapper;

import com.piano.entity.Employee;
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

    @Insert("insert into employee (full_name, username, password, phone, gender, create_time, update_time, create_user, update_user) " +
            "values(#{fullName}, #{username}, #{phone}, #{gender}, #{status}, #{createTime}, #{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);
}
