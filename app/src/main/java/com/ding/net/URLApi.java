package com.ding.net;

/**
 * Created by 丁应请 on 2016/9/20.
 */
public class URLApi {
    /**
     * 请地址
     */
    private static String NET_ADDRESS = "http://www.tngou.net/api/cook/";
    //private static String NET_ADDRESS = "http://apis.baidu.com/tngou/cook/";
    /**
     * 取得菜谱分类，可以通过分类id取得问答列表
     */
    public static String HEALTH_CLASS_IFY = NET_ADDRESS + "classify";
    /**
     * 取得菜谱列表，也可以用分类id作为参数
     */
    public static String HEALTH_LIST =  NET_ADDRESS + "list";
    /**
     * 取得菜谱名称详情，通过name取得菜谱详情
     */
    public static String HEALTH_NAME =  NET_ADDRESS + "name";
    /**
     * 取得菜谱信息，菜谱id取得该对应详细内容信息
     */
    public static String HEALTH_SHOW =  NET_ADDRESS + "show";
}
