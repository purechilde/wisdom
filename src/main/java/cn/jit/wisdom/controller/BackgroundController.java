package cn.jit.wisdom.controller;

import cn.jit.wisdom.mapper.BackgroundMapper;
import cn.jit.wisdom.pojo.Background;
import cn.jit.wisdom.pojo.User;
import cn.jit.wisdom.service.BackgroundService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/background")
public class BackgroundController {
    @Resource
    private BackgroundMapper backgroundMapper;

    @Resource
    private BackgroundService backgroundService;

    //返回所有的背调信息
    @RequestMapping("/all")
    public Object all(){
        List<Map<Object, Object>> data = new ArrayList<>();
        for (Background b : backgroundService.list()) {
            Map<Object, Object> m = new HashMap<>();
            m.put("b_id", b.getB_id());
            m.put("b_name", b.getB_name());
            m.put("b_bossphone", b.getB_bossphone());
            m.put("b_hrphone", b.getB_hrphone());
            m.put("b_reason", b.getB_reason());
            m.put("b_time", b.getB_time());
            m.put("b_record", b.getB_record());
            m.put("b_income", b.getB_income());
            m.put("b_exp", b.getB_exp());
            m.put("b_flag", b.getB_flag());
            m.put("isEdit", false);
            data.add(m);
        }
        return data;
    }

    //显示电话调查的信息
    @RequestMapping("/allPhone")
    public List<Background> allPhone(){
        List<Background> list = new ArrayList<>();
        List<Background> backgrounds = backgroundService.list();
        for(Background b:backgrounds){
            Background background = new Background();
            background.setB_id(b.getB_id());
            background.setB_bossphone(b.getB_bossphone());
            background.setB_hrphone(b.getB_hrphone());
            list.add(background);
        }
        return list;
    }

    //修改信息
    @RequestMapping("/updateBackground")
    public boolean updateBackground(String b_name,String b_bossphone,String b_hrphone,String b_reason,String b_time,String b_record,String b_income,String b_exp,String b_flag){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("b_name",b_name);
        Background background = new Background();
        background.setB_bossphone(b_bossphone);
        background.setB_hrphone(b_hrphone);
        background.setB_reason(b_reason);
        background.setB_time(b_time);
        background.setB_record(b_record);
        background.setB_income(b_income);
        background.setB_exp(b_exp);
        background.setB_flag(b_flag);
        return 1 == backgroundMapper.update(background,wrapper);
    }

    //员工填写背景调查表
    @RequestMapping("/insert")
    public Boolean insert(String b_name,String b_bossphone,String b_hrphone,String b_reason,String b_time,String b_record,String b_income,String b_exp,String b_flag){
        boolean flag = false;
        Background background = new Background();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("b_name", b_name);
        Background background1 = backgroundMapper.selectOne(wrapper);
        if (background1 == null){
            background.setB_name(b_name);
            background.setB_bossphone(b_bossphone);
            background.setB_hrphone(b_hrphone);
            background.setB_reason(b_reason);
            background.setB_time(b_time);
            background.setB_record(b_record);
            background.setB_income(b_income);
            background.setB_exp(b_exp);
            background.setB_flag(b_flag);
            if(backgroundMapper.insert(background) == 1)
                flag = true;
        }
        return flag;
    }



}
