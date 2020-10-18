package cn.lion.service;

import cn.lion.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IPermissionService {

    PageInfo findAll(QueryObject qo);

    void reload();

    void delete(Long id);
}
