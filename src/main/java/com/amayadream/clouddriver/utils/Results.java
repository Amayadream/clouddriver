package com.amayadream.clouddriver.utils;

import java.util.HashMap;

/**
 * 返回结果类
 * @author :  Amayadream
 * @date :  2016.08.04 20:38
 */
public class Results extends HashMap<String, Object> {

    private static final int SUCCESS = 1;
    private static final int ERROR = -1;

    private Results(int _code, String _msg){
        this.put("code", _code);
        this.put("msg", _msg);
    }

    public static Results success(String _msg){
        return new Results(Results.SUCCESS, _msg);
    }

    public static Results error(String _msg) {
        return new Results(Results.ERROR, _msg);
    }

}
