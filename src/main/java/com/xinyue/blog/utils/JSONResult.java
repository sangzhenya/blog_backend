package com.xinyue.blog.utils;

import org.json.JSONObject;

public class JSONResult {
    public static String fillResultStringNew(Integer status, String message, Object rawResult){
        return fillResultJSON(status, message, rawResult).toString();
    }

    public static JSONObject fillResultJSON(Integer status, String message, Object rawResult){
        return new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", new JSONObject(rawResult));
        }};
    }

    public static String fillResultString(Integer status, String message, Object rawResult){
        JSONObject object =  new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", rawResult);
        }};
        return object.toString();
    }
}
