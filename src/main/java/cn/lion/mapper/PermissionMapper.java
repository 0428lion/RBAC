package cn.lion.mapper;

import cn.lion.domain.Permission;

import java.util.List;

public interface PermissionMapper {
    List<Permission> findAll();

    void deleteAll();

    void insert(Permission permission);

    void deleteById(Long id);
}
