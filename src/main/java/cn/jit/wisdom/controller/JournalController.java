package cn.jit.wisdom.controller;


import cn.jit.wisdom.mapper.JournalMapper;
import cn.jit.wisdom.pojo.Journal;
import cn.jit.wisdom.service.JournalService;
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
@RequestMapping("/journal")
public class JournalController {

    @Resource
    private JournalService journalService;

    @Resource
    private JournalMapper journalMapper;

    //返回所有的日志
    @RequestMapping("/all")
    public List<Journal> all(){
        return journalService.list();
    }

    //返回日志的数量
    @RequestMapping("/count")
    public Integer count(){
        return journalService.count();
    }

    //插入日志
    @RequestMapping("/insert")
    public Boolean insert(String j_name,String j_ip,String j_time,String j_op,String j_des){
        boolean flag = false;
        Journal journal = new Journal();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("j_name", j_name);
        Journal journal1 = journalMapper.selectOne(wrapper);
        if(journal1 == null){
            journal.setJ_name(j_name);
            journal.setJ_ip(j_ip);
            journal.setJ_time(j_time);
            journal.setJ_op(j_op);
            journal.setJ_des(j_des);
            if(journalMapper.insert(journal) == 1)
                flag = true;
        }
        return flag;
    }

    //更新日志
    @RequestMapping("/update")
    public Boolean update(String j_name,String j_ip,String j_time,String j_op,String j_des){
        boolean flag = false;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("j_name",j_name);
        Journal journal = new Journal();
        journal.setJ_ip(j_ip);
        journal.setJ_time(j_time);
        journal.setJ_op(j_op);
        journal.setJ_des(j_des);
        if(journalMapper.update(journal,wrapper) == 1)
            flag = true;
        return flag;
    }

    //显示登录日志管理
    @RequestMapping("/allLoginJournal")
    public List<Journal> allLoginJournal(){
        List<Journal> list = new ArrayList<>();
        List<Journal> journals = journalService.list();
        for(Journal j:journals){
            Journal journal = new Journal();
            journal.setJ_id(j.getJ_id());
            journal.setJ_name(j.getJ_name());
            journal.setJ_ip(j.getJ_ip());
            journal.setJ_time(j.getJ_time());
            list.add(journal);
        }
        return list;
    }

    //显示操作日志管理
    @RequestMapping("/allOptionJournal")
    public List<Map<Object, Object>> allOptionJournal(){
        List<Map<Object, Object>> m = new ArrayList<>();
        List<Journal> journals = journalService.list();
        for(Journal j:journals){
            Map<Object, Object> tmp = new HashMap<>();
            tmp.put("j_id", j.getJ_id());
            tmp.put("j_name", j.getJ_name());
            tmp.put("j_ip", j.getJ_ip());
            tmp.put("j_op", j.getJ_op());
            tmp.put("j_time", j.getJ_time());
            tmp.put("j_des", j.getJ_des());
            m.add(tmp);
        }
        return m;
    }
}
