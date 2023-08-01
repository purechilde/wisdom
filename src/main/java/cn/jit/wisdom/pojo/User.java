package cn.jit.wisdom.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 用户实体类
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User {
    private static final long serialVersionUID = 1L;
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer user_id;
    @TableField(value = "user_name")
    private String user_name;
    @TableField(value = "user_pwd")
    private String user_pwd;
    @TableField(value = "user_email")
    private String user_email;
    @TableField(value = "user_phone")
    private String user_phone;
}
