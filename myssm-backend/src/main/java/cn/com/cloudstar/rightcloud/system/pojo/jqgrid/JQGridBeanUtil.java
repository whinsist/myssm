package cn.com.cloudstar.rightcloud.system.pojo.jqgrid;

import com.github.pagehelper.PageInfo;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;

import java.util.List;

/**
 * @author Hong.Wu
 * @date: 12:14 2019/03/24
 */
public class JQGridBeanUtil  {

    public static JQGridBean build(List  list, int totalSize, Criteria criteria) {
//        CriteriaResult criteriaResult = criteria.getTempParam();
//        Integer currentPage = criteriaResult.getCurrentPage();
//        Integer pageSize = criteriaResult.getPageSize();

        JQGridBean  gridBean = new JQGridBean<>();
//        gridBean.setPage(currentPage);
//        gridBean.setTotal(totalSize%pageSize==0 ? totalSize/pageSize : totalSize/pageSize+1); // 总页数
//        gridBean.setRecords(totalSize);
//        gridBean.setRows(list);
        return gridBean;
    }

    public static JQGridBean buildJQGrid(List<?> dataList) {
        PageInfo page = new PageInfo<>(dataList);
        JQGridBean  gridBean = new JQGridBean<>();
        gridBean.setPage(page.getPageNum());
        gridBean.setTotal(page.getPages());
        gridBean.setRecords(page.getTotal());
        gridBean.setRows(dataList);
        return gridBean;
    }
}
