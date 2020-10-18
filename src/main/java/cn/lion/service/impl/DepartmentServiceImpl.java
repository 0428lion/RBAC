package cn.lion.service.impl;

import cn.lion.domain.Department;
import cn.lion.mapper.DepartmentMapper;
import cn.lion.query.JSONResult;
import cn.lion.query.QueryObject;
import cn.lion.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public PageInfo findAll(QueryObject queryObject) {
        // PageHelper 插件处理分页
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        // 当前页的数据
        List<Department> departments=departmentMapper.findAll();

        return new PageInfo(departments);
    }

    //删除
    @Override
    public void delete(Long id) {
        departmentMapper.delete(id);

    }


    //添加和编辑
    @Override
    public void saveOrUpdate(Department department) {
        if (department.getId() != null) {
            departmentMapper.updateById(department);
        }else {
            departmentMapper.insert(department);

        }
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }


    @Override
    public Department findById(Long id) {
        return departmentMapper.findById(id);
    }


}
