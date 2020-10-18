package cn.lion.mapper;

import cn.lion.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> findAll();

    void delete(Long id);

    void insert(Department department);


    Department findById(Long id);

    void updateById(Department department);

    List<Department> selectAll();
}
