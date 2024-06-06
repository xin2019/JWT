package org.example.enums;

public enum ErrorCodeEnum {
    PARAMS_ERROR(10001, "参数错误"),
    USER_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    NO_PERMISSION(70001, "没有权限"),
    SESSION_TIMEOUT(90001, "会话超时"),
    NO_LOGIN(90002, "未登录"),;
    private int code;
    private String msg;
    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
