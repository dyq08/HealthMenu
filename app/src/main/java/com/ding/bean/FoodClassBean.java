package com.ding.bean;

/**
 * Created by 丁应清 on 2016/9/20.
 * 菜谱分类
 */
public class FoodClassBean extends ResultList<FoodClassBean>{
    private int id;              // 分类id，需要查询该类下的列表就需要传入才参数
    private int cookclass;      // 菜谱分类 0 位顶级
    private String name;         // 分类名称
    private String title;        // 分类的标题（网页显示的标题）
    private String keywords;     // 分类的关键词（网页显示的标题）
    private String description; // 分类的描述（网页显示的标题）
    private int seq;             // 排序 从0。。。。10开始

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCookclass() {
        return cookclass;
    }

    public void setCookclass(int cookclass) {
        this.cookclass = cookclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
