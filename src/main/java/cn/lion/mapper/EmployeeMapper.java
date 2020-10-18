package cn.lion.mapper;

import cn.lion.domain.Employee;
import cn.lion.domain.Role;
import cn.lion.query.QueryObject;

import java.util.List;

public interface EmployeeMapper {


    List<Employee> selectList(QueryObject queryObject);

    void delete(Long id);

    void insert(Employee employee);

    Employee findById(Long id);

    void updateById(Employee employee);
}
