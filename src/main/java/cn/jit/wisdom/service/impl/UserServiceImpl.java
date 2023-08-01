package cn.jit.wisdom.service.impl;

import cn.jit.wisdom.mapper.UserMapper;
import cn.jit.wisdom.pojo.User;
import cn.jit.wisdom.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
