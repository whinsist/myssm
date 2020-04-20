package cn.com.cloudstar.rightcloud.system.dao.system;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.system.UserRole;
@Repository
public interface UserRoleMapper {
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
    int deleteByPrimaryKey(UserRole key);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UserRole record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(UserRole record);

    /**
     * 根据条件查询记录集
     */
    List<UserRole> selectByParams(Criteria example);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") UserRole record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") UserRole record, @Param("condition") Map<String, Object> condition);
}