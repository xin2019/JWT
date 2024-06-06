package org.example.service;

import org.example.vo.R;
import org.example.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    R login(UserVo userVo);

    R logout(String token);
}
