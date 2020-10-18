package cn.lion.web.controller;

import cn.lion.domain.Role;
import cn.lion.query.JSONResult;
import cn.lion.query.QueryObject;
import cn.lion.service.IPermissionService;
import cn.lion.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    //查数据和分页
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageInfo pageInfo=permissionService.findAll(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "/permission/list";
    }

    @RequestMapping("/reload")
    public String reload(){
        permissionService.reload();
        return "redirect:/permission/list";
    }
    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        try {
            permissionService.delete(id);
            return new JSONResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONResult().mark("删除失败，请稍后再试");
        }
    }



}
