package org.example.service.impl;

import org.apache.commons.lang.StringUtils;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVo findUser(String username,String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return null;
        }
        return userMapper.findUser(username,password);
    }

}
