package cn.com.cloudstar.rightcloud.system.dao.act;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author yuz
 * @date 2018/8/3
 */
@Repository
public interface ProcessMapper {

    /**
     * 获取所有流程定义
     * @return
     */
    List<Process> selectAll();

    /**
     * 通过流程ID获取流程定义
     * @param id
     * @return
     */
    Process selectByPrimaryKey(Long id);

    /**
     * 通过流程名称获取流程定义
     * @param code
     * @return
     */
    Process selectByProcessCode(String code);

    /**
     * 通过流程名称获取流程定义
     * @param name
     * @param orgSid
     * @return
     */
    Process selectByProcessName(@Param("name") String name, @Param("orgSid") Long orgSid);

    /**
     * 通过业务code获取流程定义
     * @param code
     * @param orgSid
     * @return
     */
    Process selectByBusinessCode(@Param("code") String code, @Param("orgSid") Long orgSid);

    /**
     * 通过业务code获取流程定义(包含已删除)
     * @param code
     * @param orgSid
     * @return
     */
    Process selectByBusinessCodeWithDeleted(@Param("code") String code, @Param("orgSid") Long orgSid);

    /**
     * 根据参数查流程定义
     * @param process
     * @return
     */
    List<Process> selectByParams(Process process);

    /**
     * 通过主键更新不为空的字段
     * @param process
     */
    void updateByPrimaryKeySelective(Process process);

    /**
     * 保存属性不为空的记录
     * @param process 流程定义记录
     * @return
     */
    int insertSelective(Process process);

    /**
     * 删除流程定义
     * @param id
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 清空流程定义
     */
    void clear();

    /**
     * 通过orgSid统计定义流程数量
     * @param orgSid
     * @return
     */
    int countByOrgSid(Long orgSid);
}
