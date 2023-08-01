package cn.jit.wisdom.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("background")
public class Background {
    private static final long serialVersionUID = 1L;
    //背景id
    @TableId(value = "b_id",type = IdType.AUTO)
    private Integer b_id;
    //背调人员名字
    @TableField(value = "b_name")
    private String b_name;
    //上家企业的上司电话
    @TableField(value = "b_bossphone")
    private String b_bossphone;

    //上家企业的hr电话
    @TableField(value = "b_hrphone")
    private String b_hrphone;
    @TableField(value = "b_reason")
    //离职原因
    private String b_reason;
    @TableField(value = "b_time")
    //离职时间
    private String b_time;
    @TableField(value = "b_record")
    //有无不良记录
    private String b_record;
    @TableField(value = "b_income")
    //上家企业的收入
    private String b_income;
    @TableField(value = "b_exp")
    //工作经验
    private String b_exp;
    //背调的状态
    @TableField(value = "b_flag")
    private String b_flag;

}
