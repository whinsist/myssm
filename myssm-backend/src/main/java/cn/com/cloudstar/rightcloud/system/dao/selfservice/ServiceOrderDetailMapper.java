package cn.com.cloudstar.rightcloud.system.dao.selfservice;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceOrderDetail;

/**
 * @author Hong.Wu
 * @date: 14:28 2019/11/03
 */
@Repository
public interface ServiceOrderDetailMapper {

    int insertSelective(ServiceOrderDetail record);

    List<ServiceOrderDetail> selectByParams(Criteria criteria);
}
