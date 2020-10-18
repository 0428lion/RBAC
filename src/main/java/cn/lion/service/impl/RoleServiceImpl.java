package cn.lion.service.impl;


import cn.lion.domain.Role;
import cn.lion.mapper.RoleMapper;
import cn.lion.query.QueryObject;
import cn.lion.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public PageInfo findAll(QueryObject queryObject) {
        // PageHelper 插件处理分页
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        // 当前页的数据
        List<Role> roles=roleMapper.findAll();

        return new PageInfo(roles);
    }

    //删除
    @Override
    public void delete(Long id) {
        roleMapper.delete(id);

    }

    @Override
    public Role findById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public void saveOrUpdate(Role role) {
        if (role.getId() != null) {
            roleMapper.updateById(role);
        }else {
            roleMapper.insert(role);

        }
    }


}
