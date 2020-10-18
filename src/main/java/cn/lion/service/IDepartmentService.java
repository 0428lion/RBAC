package cn.lion.service;

import cn.lion.domain.Department;
import cn.lion.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    PageInfo findAll(QueryObject queryObject);

    void delete(Long id);

    Department findById(Long id);

    void saveOrUpdate(Department department);


    List<Department> listAll();
}
