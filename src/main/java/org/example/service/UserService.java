package org.example.service;

import org.example.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /*
    根据用户名和密码查找用户
     */
    UserVo findUser(String username,String password);
}
