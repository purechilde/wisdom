package cn.jit.wisdom.controller;


import cn.jit.wisdom.mapper.EmployeeMapper;
import cn.jit.wisdom.pojo.Employee;
import cn.jit.wisdom.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private EmployeeMapper employeeMapper;


    //返回所有的员工信息
    @RequestMapping("/all")
    public Object all() {
        List<Map<Object, Object>> data = new ArrayList<>();
        for (Employee e : employeeService.list()) {
            Map<Object, Object> m = new HashMap<>();
            m.put("emp_id", e.getEmp_id());
            m.put("emp_num", e.getEmp_num());
            m.put("emp_name", e.getEmp_name());
            m.put("emp_dep", e.getEmp_dep());
            m.put("emp_pos", e.getEmp_pos());
            m.put("emp_phone", e.getEmp_phone());
            m.put("emp_status", e.getEmp_status());
            m.put("emp_exp", e.getEmp_exp());
            m.put("emp_log", e.getEmp_log());
            m.put("emp_contract", e.getEmp_contract());
            m.put("emp_resume", e.getEmp_resume());
            m.put("isEdit", false);
            data.add(m);
        }
        return data;
    }

    //返回员工表的数量
    @RequestMapping("/count")
    public int count() {
        return employeeService.count();
    }

    //返回员工管理中的员工
    @RequestMapping("/allEmployee")
    public List<Employee> allEmployee() {
        List<Employee> list = new ArrayList<>();
        List<Employee> employees = employeeService.list();
        for (Employee e : employees) {
            Employee employee = new Employee();
            employee.setEmp_id(e.getEmp_id());
            employee.setEmp_num(e.getEmp_num());
            employee.setEmp_name(e.getEmp_name());
            employee.setEmp_dep(e.getEmp_dep());
            employee.setEmp_pos(e.getEmp_pos());
            employee.setEmp_status(e.getEmp_status());
            employee.setEmp_phone(e.getEmp_phone());
            list.add(employee);
        }
        return list;
    }

    //员工管理中的添加员工功能(员工工作经验、员工工作记录、简历和合同可以为空)
    @RequestMapping("/add")
    public Boolean add(String emp_num, String emp_name, String emp_dep, String emp_pos, String emp_phone,
                       String emp_exp, String emp_log, String emp_contract, String emp_resume) {
        boolean flag = false;
        Employee employee = new Employee();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_num", emp_num);
        //判断是否存在该员工 工号是唯一的
        Employee employee1 = employeeMapper.selectOne(wrapper);
        if (employee1 == null) {
            employee.setEmp_num(emp_num);
            employee.setEmp_name(emp_name);
            employee.setEmp_dep(emp_dep);
            employee.setEmp_pos(emp_pos);
            employee.setEmp_phone(emp_phone);
            employee.setEmp_exp(emp_exp);
            employee.setEmp_log(emp_log);
            employee.setEmp_contract(emp_contract);
            employee.setEmp_resume(emp_resume);
            int insert = employeeMapper.insert(employee);
            if (insert == 1)
                flag = true;
        }
        return flag;
    }

    //员工离职(背调发现信息参数不对才会离职)
    @RequestMapping("/quit")
    public boolean quit(String emp_num) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_num", emp_num);
        Employee employee = new Employee();
        employee.setEmp_status("quit");
        return 1 == employeeMapper.update(employee, wrapper);
    }


    //根据id查询员工
    @GetMapping("/byId/{id}")
    public Employee byId(@PathVariable int id) {
        return employeeMapper.selectById(id);
    }

    //根据工号查询员工
    @RequestMapping("/byExpNum")
    public Employee byExpNum(Integer num) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_num", num);
        return employeeMapper.selectOne(wrapper);
    }

    //更新员工信息
    @RequestMapping("/updateEmployee")
    public boolean updateEmployee(String emp_num, String emp_dep, String emp_pos, String emp_phone) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_num", emp_num);
        Employee employee = new Employee();
        employee.setEmp_dep(emp_dep);
        employee.setEmp_pos(emp_pos);
        employee.setEmp_phone(emp_phone);
        return 1 == employeeMapper.update(employee, wrapper);
    }

    //删除员工
    @RequestMapping("/deleteEmployee")
    public boolean deleteEmployee(Integer emp_id) {
        return employeeMapper.deleteById(emp_id) == 1 ? true : false;
    }


    //返回员工工作经历中的员工
    @RequestMapping("/allExperience")
    public List<Employee> allExperience() {
        List<Employee> list = new ArrayList<>();
        List<Employee> employees = employeeService.list();
        for (Employee e : employees) {
            Employee employee = new Employee();
            employee.setEmp_id(e.getEmp_id());
            employee.setEmp_num(e.getEmp_num());
            employee.setEmp_name(e.getEmp_name());
            employee.setEmp_dep(e.getEmp_dep());
            employee.setEmp_pos(e.getEmp_pos());
            employee.setEmp_exp(e.getEmp_exp());
            list.add(employee);
        }
        return list;
    }

    //更新员工工作经历
    @RequestMapping("/updateExp")
    public boolean updateExp(String emp_num, String emp_exp) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_num", emp_num);
        Employee employee = new Employee();
        employee.setEmp_exp(emp_exp);
        return 1 == employeeMapper.update(employee, wrapper);
    }

    //更新员工工作记录
    @RequestMapping("/updateLog")
    public boolean updateLog(String emp_num, String emp_log) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_num", emp_num);
        Employee employee = new Employee();
        employee.setEmp_log(emp_log);
        return 1 == employeeMapper.update(employee, wrapper);
    }

    //查看简历
    @RequestMapping("/byResume")
    public List<Employee> byResume(String num) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.select("emp_resume")
                .eq("emp_num", num);
        return employeeMapper.selectList(wrapper);
    }

    //查看合同
    @RequestMapping("/byContract")
    public List<Employee> byContract(String num) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.select("emp_contract")
                .eq("emp_num", num);
        return employeeMapper.selectList(wrapper);
    }

}
