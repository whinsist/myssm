package cn.com.cloudstar.rightcloud.system.dao.selfservice;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.com.cloudstar.rightcloud.system.entity.selfservice.ProcessNode;

/**
 *
 * @author yuz
 * @date 2018/8/3
 */
@Repository
public interface ProcessNodeMapper {

    /**
     * 查询流程节点列表
     * @param versionId 流程版本ID
     * @return
     */
    List<ProcessNode> selectByVersionId(Long versionId);

    /**
     * 查询流程审核节点列表
     * @param versionId
     * @return
     */
    List<ProcessNode> selectAuditNodeByVersionId(Long versionId);

    /**
     * 查询关闭节点列表
     * @param versionId
     */
    List<ProcessNode> selectCloseNodeByVersionId(Long versionId);

    /**
     * 通过流程标识查询流程节点
     * @param processIdentify
     * @return
     */
    List<ProcessNode> selectAuditNodeByProcessIdentify(String processIdentify);

    /**
     * 查询流程节点
     * @param id 流程节点ID
     * @return
     */
    ProcessNode selectByPrimaryKey(Long id);

    /**
     * 保存属性不为空的记录
     * @param processNode
     */
    void insertSelective(ProcessNode processNode);

    /**
     * 通过主键更新不为空的字段
     * @param processNode
     */
    void updateByPrimaryKeySelective(ProcessNode processNode);

    /**
     * 删除流程节点
     * @param id
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 通过流程版本ID删除节点
     * @param versionId
     */
    void deleteByVersionId(Long versionId);

    /**
     * 通过流程定义ID删除节点
     * @param processId
     */
    void deleteByProcessId(Long processId);

    /**
     * 清空流程版本
     */
    void clear();
}
