package cn.jit.wisdom.controller;


import cn.jit.wisdom.mapper.UserMapper;
import cn.jit.wisdom.pojo.User;
import cn.jit.wisdom.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private JournalController journalController;

    //返回所有用户
    @RequestMapping("/all")
    public List<User> all(){
        List<User> list = userService.list();
        return list;
    }

    //返回用户表的数量
    @RequestMapping("/count")
    public int count(){
        return userService.count();
    }

    //插入用户数据
    @RequestMapping("/register")
    public Boolean insert(String user_name,String user_pwd,String user_cpwd,String user_email,String user_phone) {
        boolean flag = false;
        User user = new User();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name", user_name);
        User user1 = userMapper.selectOne(wrapper);
        //判断用户名是否存在
        if (user1 == null) {
            if (user_pwd.equals(user_cpwd)) {
                user.setUser_name(user_name);
                user.setUser_pwd(user_pwd);
                user.setUser_email(user_email);
                user.setUser_phone(user_phone);
                int insert = userMapper.insert(user);
                if (insert == 1) {
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date();
                    journalController.insert(user_name, "127.0.0.1", formatter.format(date), "注册", "用户" + user_name + "于" + formatter.format(date) + "完成注册");
                    return true;
                }
            }
        }
        return false;
    }

    //根据id查询用户
    @GetMapping("/byId/{id}")
    public User byId(@PathVariable int id){
        return userMapper.selectById(id);
    }

    //根据邮箱查询用户
    @RequestMapping("/byEmail")
    public User byEmail(String email){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_email",email);
        return userMapper.selectOne(wrapper);
    }

    //根据用户名查询用户
    @RequestMapping("/byName")
    public User byName(String name){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",name);
        return userMapper.selectOne(wrapper);
    }

    //根据电话查询用户
    @RequestMapping("/byPhone")
    public User byPhone(String phone){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_phone",phone);
        return userMapper.selectOne(wrapper);
    }

    //修改密码
    @RequestMapping("/updatePwd")
    public int updatePwd(String name,String nPwd){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",name);
        User user = new User();
        user.setUser_pwd(nPwd);
        return userMapper.update(user, wrapper);
    }

    //修改邮箱
    @RequestMapping("/updateEmail")
    public int updateEmail(String name,String nEmail){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",name);
        User user = new User();
        user.setUser_email(nEmail);
        return userMapper.update(user, wrapper);
    }

    //修改电话
    @RequestMapping("/updatePhone")
    public int updatePhone(String name,String nPhone){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",name);
        User user = new User();
        user.setUser_phone(nPhone);
        return userMapper.update(user, wrapper);
    }

    //登录
    @RequestMapping("/login")
    public Object login(String name,String pwd){
        Map<Object, Object> data = new HashMap<>();
        if(name == null) {
            data.put("res", false);
            return data;
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",name);
        if(userMapper.selectOne(wrapper)==null){
            data.put("res", false);
            return data;
        }
        else{
            User user = userMapper.selectOne(wrapper);
            if (user.getUser_pwd().equals(pwd)) {
                data.put("res", true);
                data.put("token", user.getUser_id());
               return data;
            }
            else {
                data.put("res", false);
                return data;
            }
        }
    }

    @RequestMapping("/getInfo")
    public User getInfo(Integer token) {
        return userService.getById(token);
    }

    @RequestMapping("/logout")
    public boolean logout(Integer token) {
        return true;
    }




}
