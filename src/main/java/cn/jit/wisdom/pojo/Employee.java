package cn.jit.wisdom.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

//员工实体类
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("employee")
public class Employee {
    private static final long serialVersionUID = 1L;
    //员工id
    @TableId(value = "emp_id",type = IdType.AUTO)
    private Integer emp_id;
    //员工工号
    @TableField(value = "emp_num")
    private String emp_num;
    //员工姓名
    @TableField(value = "emp_name")
    private String emp_name;
    //员工部门
    @TableField(value = "emp_dep")
    private String emp_dep;
    //员工职位
    @TableField(value = "emp_pos")
    private String emp_pos;
    //员工在职状态（在职/离职）
    @TableField(value = "emp_status")
    private String emp_status;
    //员工电话
    @TableField(value = "emp_phone")
    private String emp_phone;
    //员工工作经验
    @TableField(value = "emp_exp")
    private String emp_exp;
    //员工工作记录
    @TableField(value = "emp_log")
    private String emp_log;
    //员工合同
    @TableField(value = "emp_contract")
    private String emp_contract;
    //员工简历
    @TableField(value = "emp_resume")
    private String emp_resume;

}
