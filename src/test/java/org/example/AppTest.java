package org.example;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest 

{
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testApp()
    {
        String slat = "1234@fsea56";//用于密钥加密的盐值
        String password = DigestUtils.md5Hex("123" + slat);
        userMapper.insertUser("cx", password);
    }
}
