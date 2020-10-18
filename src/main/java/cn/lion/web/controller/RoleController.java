package cn.lion.web.controller;

import cn.lion.domain.Department;
import cn.lion.domain.Role;
import cn.lion.query.JSONResult;
import cn.lion.query.QueryObject;
import cn.lion.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    //查数据和分页
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageInfo pageInfo=roleService.findAll(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "/role/list";
    }
    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){

        try {
            roleService.delete(id);
            return new JSONResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONResult().mark("删除失败，请稍后再试");
        }
    }

    //编辑
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null) {
            Role role=roleService.findById(id);
            model.addAttribute("entity",role);
        }
        return "/role/input";
    }

    //增加和更新
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role){
        roleService.saveOrUpdate(role);

        return "redirect:/role/list";
    }

}
