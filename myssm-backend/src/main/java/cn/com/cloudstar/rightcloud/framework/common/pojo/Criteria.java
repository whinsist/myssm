/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 公用条件查询类
 */
public class Criteria implements Serializable {
    /**
     * 是否相异
     */
    protected boolean distinct;
    /**
     * 排序字段
     */
    protected String orderByClause;
    /**
     * 存放条件查询值
     */
    private Map<String, Object> condition;

    private Integer pageSize;
    private Integer pageNum;

    protected Criteria(Criteria example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.distinct = example.distinct;
        this.pageSize = example.pageSize;
        this.pageNum = example.pageNum;
    }

    public Criteria(String key, Object value) {
        this();
        condition.put(key, value);
    }

    public Criteria() {
        condition = new HashMap<String, Object>();
    }

    public void clear() {
        condition.clear();
        orderByClause = null;
        distinct = false;
        this.pageSize = null                   ;
        this.pageNum = null;
    }

    /**
     * @param condition 查询的条件名称
     * @param value     查询的值
     */
    public Criteria put(String condition, Object value) {
        this.condition.put(condition, value);
        return this;
    }

    /**
     * 得到键值，C层和S层的参数传递时取值所用<br>
     * 自行转换对象
     *
     * @param key 键值
     * @return 返回指定键所映射的值
     */
    public Object get(String key) {
        return this.condition.get(key);
    }

    /**
     * @param orderByClause 排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @param distinct 是否相异
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


}