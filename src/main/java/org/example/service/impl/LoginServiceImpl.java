package org.example.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.example.constant.LoginConstant;
import org.example.service.LoginService;
import org.example.service.UserService;
import org.example.utils.JWTUtils;
import org.example.vo.R;
import org.example.enums.ErrorCodeEnum;
import org.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final String slat = "1234@fsea56";//用于密钥加密的盐值
    @Override
    public R login(UserVo userVo) {
        /*
        1、检查参数是否合法
        2、根据用户名密码去user表查询
        3、如果不存在，返回错误信息，登录失败
        4、如果存在，生成token，返回给前端
        5、token存入redis当中，redis中存放token，user信息，先检验token的合法性，然后再去redis判断是否存在
         */
        if(StringUtils.isBlank(userVo.getUsername()) || StringUtils.isBlank(userVo.getPassword())){
            return R.error(ErrorCodeEnum.PARAMS_ERROR.getCode(), ErrorCodeEnum.PARAMS_ERROR.getMsg());
        }
        //一般数据库存储的为加密的密码，加了盐值
        String password = DigestUtils.md5Hex(userVo.getPassword() + slat);
        String username = userVo.getUsername();
        UserVo user = userService.findUser(username,password);
        if(user == null){
            return R.error(ErrorCodeEnum.USER_PWD_NOT_EXIST.getCode(),  ErrorCodeEnum.USER_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user), LoginConstant.REDIS_EXPIRE_TIME, TimeUnit.DAYS);
        return R.ok("200",token);
    }
}
