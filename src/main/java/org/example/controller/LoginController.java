package org.example.controller;

import org.example.service.LoginService;
import org.example.vo.R;
import org.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public R login(@RequestBody UserVo userVo){
        return loginService.login(userVo);
    }
    @GetMapping("/logout")
    public R logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);
    }
}
