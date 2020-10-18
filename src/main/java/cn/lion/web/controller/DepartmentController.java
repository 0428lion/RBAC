package cn.lion.web.controller;

import cn.lion.anno.RequiresPermission;
import cn.lion.domain.Department;
import cn.lion.query.JSONResult;
import cn.lion.query.QueryObject;
import cn.lion.service.IDepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    //查数据和分页
    @RequestMapping("/list")
    @RequiresPermission({"部门列表权限","cn.lion.web.controller.DepartmentController.list"})
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageInfo pageInfo=departmentService.findAll(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "/department/list";
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermission({"部门删除权限","cn.lion.web.controller.DepartmentController.delete"})
    public JSONResult delete(Long id){

        try {
            departmentService.delete(id);
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
            Department department=departmentService.findById(id);
            model.addAttribute("entity",department);
        }

        return "/department/input";
    }

    //增加和更新
    @RequestMapping("/saveOrUpdate")
    @RequiresPermission({"部门添加或者更新权限","cn.lion.web.controller.DepartmentController.saveOrUpdate"})
    public String saveOrUpdate(Department department) {
        departmentService.saveOrUpdate(department);

        return "redirect:/department/list";
    }



}
