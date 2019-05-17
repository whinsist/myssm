/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Grid返回对象
 *
 * @author 刘洋
 */
public class BaseGridReturn {

    /**
     * 总数据条擿
     */
    private long totalRows;

    /**
     * 显示数据对象
     */
    private Object data;

    /**
     * 显示数据列表
     */
    private List<?> dataList;

    private int totalPages;

    public BaseGridReturn() {
    }

    public BaseGridReturn(List<?> dataList) {
        PageInfo page = new PageInfo<>(dataList);
        this.totalRows = page.getTotal();
        this.dataList = page.getList();
        this.totalPages = page.getPages();
    }

    public BaseGridReturn(PageInfo<?> page) {
        this.totalRows = page.getTotal();
        this.dataList = page.getList();
        this.totalPages = page.getPages();
    }

    public BaseGridReturn(PageInfo<?> page, Object data) {
        this.totalRows = page.getTotal();
        this.data = data;
        this.dataList = page.getList();
    }

    /**
     * 取得总数据条擿
     *
     * @return 总数据条擿
     */
    public long getTotalRows() {
        return totalRows;
    }

    /**
     * 设置总数据条擿
     */
    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * 取得显示数据列表
     *
     * @return data
     */
    public List<?> getDataList() {
        return dataList;
    }

    /**
     * 设置显示数据列表
     */
    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
