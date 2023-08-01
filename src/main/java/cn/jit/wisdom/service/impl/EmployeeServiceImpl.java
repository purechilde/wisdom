package cn.jit.wisdom.service.impl;

import cn.jit.wisdom.mapper.EmployeeMapper;
import cn.jit.wisdom.pojo.Employee;
import cn.jit.wisdom.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
