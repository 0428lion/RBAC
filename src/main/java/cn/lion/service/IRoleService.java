package cn.lion.service;

import cn.lion.domain.Role;
import cn.lion.query.QueryObject;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;

@Controller

public interface IRoleService {
    PageInfo findAll(QueryObject qo);

    void delete(Long id);

    Role findById(Long id);

    void saveOrUpdate(Role role);
}
