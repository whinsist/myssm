package cn.com.cloudstar.rightcloud.system.dao.selfservice;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.com.cloudstar.rightcloud.system.entity.selfservice.ProcessVersion;


/**
 *
 * @author yuz
 * @date 2018/8/3
 */
@Repository
public interface ProcessVersionMapper {

    /**
     * 查询流程版本列表
     * @param processId 流程定义ID
     * @return
     */
    List<ProcessVersion> selectByProcessId(Long processId);

    /**
     * 查询流程版本
     * @param id 流程版本ID
     * @return
     */
    ProcessVersion selectByPrimaryKey(Long id);

    /**
     * 保存属性不为空的记录
     * @param processVersion 流程版本
     */
    void insertSelective(ProcessVersion processVersion);

    /**
     * 通过主键更新不为空的字段
     * @param processVersion
     */
    void updateByPrimaryKeySelective(ProcessVersion processVersion);

    /**
     * 删除流程版本
     * @param id
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 通过流程定义ID删除版本
     * @param processId
     */
    void deleteByProcessId(Long processId);

    /**
     * 清空流程版本
     */
    void clear();
}
