package com.ding.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {
	private static Gson gson = new Gson();

	public static <T> T object(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
	}

	public static String string(Object object) {
		return gson.toJson(object);
	}
	public static <T> List<T> getArrays(String jsonString, Class<T> cls) {

        List<T> list = new ArrayList<T>();
        try {
            JSONArray jsonArray = new JSONObject(jsonString).getJSONArray("");
            Gson gson = new Gson();
            list = gson.fromJson(jsonArray.toString(), new TypeToken<List<T>>() {
            }.getType());  
  
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return list;  
    }

    public static <T> List<T> jsonToArray(String jsonString, TypeToken<List<T>> token) {
//        if (StringUtils.isEmpty(jsonString)) {
//            return null;
//        }
        List<T> list = new ArrayList<T>();
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, token.getType());
        return list;
    }
    public static String Joint(String url, Map<String, String> params) {
        if (url.indexOf("?") < 0) {
            url += "?";
        }
        if (params != null) {
            for (String name : params.keySet()) {
                try {
                    url += "&" + name + "="
                            + URLEncoder.encode(params.get(name), "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    System.out.println("Joint===" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return url;
    }
}
