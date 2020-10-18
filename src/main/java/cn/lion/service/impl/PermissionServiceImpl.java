package cn.lion.service.impl;

import cn.lion.anno.RequiresPermission;
import cn.lion.domain.Permission;
import cn.lion.mapper.PermissionMapper;
import cn.lion.query.QueryObject;
import cn.lion.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public PageInfo findAll(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> permissions=permissionMapper.findAll();
        return new PageInfo(permissions);
    }

    @Override
    public void reload() {
        // 删除所有的权限
        permissionMapper.deleteAll();
        // 从spring容器中获取所有的controller
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Controller.class);
        Collection<Object> collections = beansWithAnnotation.values();
        //  遍历所有的controller,获取conntroller中的所有 方法对象
        for (Object collection : collections) {
            // 这个controller是真实的controller的代理类
            // 从所有的contoller中获取所有的方法
            Method[] methods = collection.getClass().getDeclaredMethods();
            for (Method method : methods) {
                //判断是否有permission注解
                if(method.isAnnotationPresent(RequiresPermission.class)){
                    // 从所有的方法上获取RequestPermission注解
                    RequiresPermission requiresPermission = method.getAnnotation(RequiresPermission.class);
                    // 从注解中获取 权限的名称和表达式
                    String[] strings = requiresPermission.value();
                    String name=strings[0];
                    String expression=strings[1];
                    Permission permission=new Permission();
                    permission.setName(name);
                    permission.setExpression(expression);
                    permissionMapper.insert(permission);

                }
            }


        }
        // 把权限信息保存到数据库中

    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteById(id);
    }
}
