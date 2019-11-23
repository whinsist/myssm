package cn.com.cloudstar.rightcloud.system.service.process;

import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrderRecord; /**
 * @author Hong.Wu
 * @date: 14:22 2019/11/03
 */
public interface OrderRecordService {

    void insertSelective(ServiceOrderRecord orderRecord);
}
