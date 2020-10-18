package cn.lion.web.controller;

import cn.lion.anno.RequiresPermission;
import cn.lion.domain.Department;
import cn.lion.domain.Employee;
import cn.lion.domain.Role;
import cn.lion.query.EmployeeQueryObject;
import cn.lion.query.JSONResult;
import cn.lion.query.QueryObject;
import cn.lion.service.IDepartmentService;
import cn.lion.service.IEmployeeService;
import cn.lion.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;

    //查数据和分页
    @RequestMapping("/list")
    @RequiresPermission({"员工列表权限","cn.lion.web.controller.EmployeeController.list"})
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        PageInfo pageInfo = employeeService.listAll(qo);
        List<Department> departments = departmentService.listAll();
        model.addAttribute("depts", departments);
        model.addAttribute("pageInfo", pageInfo);
        return "/employee/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermission({"员工列表权限","cn.lion.web.controller.EmployeeController.delete"})
    public JSONResult delete(Long id) {

        try {
            employeeService.delete(id);
            return new JSONResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONResult().mark("删除失败，请稍后再试");
        }
    }

    //编辑
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) {
            Employee employee = employeeService.findById(id);
            model.addAttribute("entity", employee);
            System.out.println("111");
        }
        List<Department> departments = departmentService.listAll();
        model.addAttribute("depts", departments);
        return "/employee/input";
    }

    //增加和更新
    @RequestMapping("/saveOrUpdate")
    @RequiresPermission({"员工列表权限","cn.lion.web.controller.EmployeeController.saveOrUpdate"})
    public String saveOrUpdate(Employee employee) {
        employeeService.saveOrUpdate(employee);

        return "redirect:/employee/list";
    }

}
