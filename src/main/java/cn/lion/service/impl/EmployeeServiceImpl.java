package cn.lion.service.impl;

import cn.lion.domain.Employee;
import cn.lion.domain.Role;
import cn.lion.mapper.EmployeeMapper;
import cn.lion.query.EmployeeQueryObject;
import cn.lion.query.QueryObject;
import cn.lion.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public PageInfo listAll(EmployeeQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Employee> employees=employeeMapper.selectList(qo);
        return new PageInfo(employees);
    }

    @Override
    public void delete(Long id) {
        employeeMapper.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeMapper.findById(id);
    }


    @Override
    public void saveOrUpdate(Employee employee) {
        if (employee.getId() != null) {
            //编辑
            employeeMapper.updateById(employee);
        }else {
            //添加
            employeeMapper.insert(employee);
        }
    }


}
