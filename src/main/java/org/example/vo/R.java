package org.example.vo;


import cn.hutool.http.HttpStatus;

import java.util.HashMap;

/*
    * 返回数据
 */
public class R extends HashMap<String,Object> {
    public R() {
        put("code", 0);
        put("msg", "success");
    }
    public static R error(){
        return error(HttpStatus.HTTP_INTERNAL_ERROR, "Internal Server Error.");
    }

    public static R error(int httpInternalError, String s) {
        R r = new R();
        r.put("code", httpInternalError);
        r.put("msg", s);
        return r;
    }
    public static R error(String msg){
        return error(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public static R ok(String msg){
        R r = new R();
        r.put("msg", msg);
        return r;
    }
    public static R ok(String code,String msg){
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
    public static R ok(){
        return new R();
    }
    public static R ok(HashMap<String, Object> map){
        R r = new R();
        r.putAll(map);
        return r;
    }
}
