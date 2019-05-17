package cn.com.cloudstar.rightcloud.system.pojo.jqgrid;


import java.io.Serializable;
import java.util.List;

/**
 * <JQGrid -  工具类>
 * <功能详细描述>
 *
 * @author  YuHaiBo
 * @version  [版本号, 2016年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class JQGridBean<T> implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    // 当前页
    private long page;

    // 总页数
    private long total;

    // 总条数
    private long records;

    // 当前也列表
    private List<T> rows;

    public long getPage()
    {
        return page;
    }

    public void setPage(long page)
    {
        this.page = page;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public long getRecords()
    {
        return records;
    }

    public void setRecords(long records)
    {
        this.records = records;
    }

    public List<T> getRows()
    {
        return rows;
    }

    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }
}
