package org.example.vo;

import lombok.Data;

@Data
//@TableName("user")
public class UserVo {
//    @TableId("user_id")
    private Long id;
    private String username;
    private String password;
}
