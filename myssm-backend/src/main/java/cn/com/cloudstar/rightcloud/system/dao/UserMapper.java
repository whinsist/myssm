package cn.com.cloudstar.rightcloud.system.dao;

import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.PermissionAop;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface UserMapper {
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
    int deleteByPrimaryKey(Long userSid);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(User record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(User record);

    /**
     * 根据条件查询记录集
     */
    List<User> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    User selectByPrimaryKey(Long userSid);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") User record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") User record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(User record);

    List<User> selectByPageNumSize(@Param("user") User User, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //@PermissionAop
    List<User> selectByPermission(Criteria example);
}