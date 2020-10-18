package cn.lion.mapper;

import cn.lion.domain.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> findAll();

    void delete(Long id);

    Role findById(Long id);

    void updateById(Role role);

    void insert(Role role);
}
