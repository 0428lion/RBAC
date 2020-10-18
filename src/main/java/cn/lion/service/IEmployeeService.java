package cn.lion.service;

import cn.lion.domain.Employee;
import cn.lion.domain.Role;
import cn.lion.query.EmployeeQueryObject;
import cn.lion.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IEmployeeService {

    PageInfo listAll(EmployeeQueryObject qo);

    void delete(Long id);

    Employee findById(Long id);

    void saveOrUpdate(Employee employee);
}
