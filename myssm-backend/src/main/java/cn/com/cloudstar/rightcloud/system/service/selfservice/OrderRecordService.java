package cn.com.cloudstar.rightcloud.system.service.selfservice;

import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceOrderRecord; /**
 * @author Hong.Wu
 * @date: 14:22 2019/11/03
 */
public interface OrderRecordService {

    void insertSelective(ServiceOrderRecord orderRecord);
}
