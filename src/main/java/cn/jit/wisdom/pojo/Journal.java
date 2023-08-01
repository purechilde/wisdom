package cn.jit.wisdom.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("journal")
//日志实体类
public class Journal {
    private static final long serialVersionUID = 1L;
    //日志id
    @TableId(value = "j_id",type = IdType.AUTO)
    private Integer j_id;
    //日志账号名
    @TableField(value = "j_name")
    private String j_name;
    //日志ip地址
    @TableField(value = "j_ip")
    private String j_ip;
    //日志时间
    @TableField(value = "j_time")
    private String j_time;
    //日志操作
    @TableField(value = "j_op")
    private String j_op;
    //日志描述
    @TableField(value = "j_des")
    private String j_des;
}
