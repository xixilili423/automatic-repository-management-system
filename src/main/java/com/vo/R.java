package com.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: R
 * Date: 2023/04/12
 * Description: 该类用于传输数据，为Response文件，返回值时使用
 */
@Data
public class R {

    //是否成功
    private Boolean success;
    //返回消息
    private String message;
    //返回数据
    public Map<String, Object> data = new HashMap<>();

    // 把构造方法私有化
    public R() {}

    // 成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setMessage("成功");
        return r;
    }

    // 失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setMessage("失败");
        return r;
    }

    // 消息
    public R message(String message){
        this.setMessage(message);
        return this;
    }

    // 数据
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    //数据
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
