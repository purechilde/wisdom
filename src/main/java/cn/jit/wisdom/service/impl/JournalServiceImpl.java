package cn.jit.wisdom.service.impl;

import cn.jit.wisdom.mapper.JournalMapper;
import cn.jit.wisdom.pojo.Journal;
import cn.jit.wisdom.service.JournalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class JournalServiceImpl extends ServiceImpl<JournalMapper, Journal> implements JournalService {

}
