package cn.com.cloudstar.rightcloud.system.dao.system;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.system.UserOrg;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface UserOrgMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByParams(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(UserOrg key);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UserOrg record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(UserOrg record);

    /**
     * 根据条件查询记录集
     */
    List<UserOrg> selectByParams(Criteria example);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") UserOrg record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") UserOrg record, @Param("condition") Map<String, Object> condition);
}