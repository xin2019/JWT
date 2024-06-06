package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.vo.UserVo;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password} limit 1")
    UserVo findUser(String username,String password);

    /*
    测试方法使用的，正常情况传入参数为实体，这里为了简单，就直接传入两个参数
     */
    @Insert("insert into user(username,password) values(#{username},#{password})")
    void insertUser(String username,String password);
}
