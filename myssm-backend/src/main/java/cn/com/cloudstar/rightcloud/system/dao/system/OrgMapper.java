package cn.com.cloudstar.rightcloud.system.dao.system;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.system.Org;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgMapper {
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
    int deleteByPrimaryKey(Long orgSid);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Org record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Org record);

    /**
     * 根据条件查询记录集
     */
    List<Org> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    Org selectByPrimaryKey(Long orgSid);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") Org record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") Org record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Org record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Org record);

    List<Org> findByUserSid(Criteria criteria);
}