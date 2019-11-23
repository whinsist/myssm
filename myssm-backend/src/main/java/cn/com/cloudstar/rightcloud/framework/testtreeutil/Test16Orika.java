package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import cn.com.cloudstar.rightcloud.framework.common.pojo.LicenseVo;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.SystemUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.TripleDES;

/**
 * @author Hong.Wu
 * @date: 13:54 2019/10/11
 */
public class Test16Orika {


    public static void main(String[] args) {
        //对象复制
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        OrikaVo user = new OrikaVo("beyondLi", 18);
//        CopyOrikaVo copyOrikaVo = mapperFactory.getMapperFacade(OrikaVo.class, CopyOrikaVo.class).map(CopyOrikaVo.class);
        CopyOrikaVo copyOrikaVo = mapperFactory.getMapperFacade().map(user, CopyOrikaVo.class);
        System.out.println(copyOrikaVo);

//        //复制对象(属性名不同) 方法
//        //首先使用classMap将两个类的字节码存好,使用field将双方名字对应,若有多个不同可连续使用field,使用byDefault将其余相同名字的自动拷贝(如果不使用则只会拷贝被field配置的属性),最后使用register使其生效
//        mapperFactory.classMap(User.class,CopyUser.class).field("age", "copyAge").byDefault().register();
//        CopyUser copyUser = mapperFactory.getMapperFacade().map(user, CopyUser.class);
//
//
//        //进行属性名匹配
//        mapperFactory.classMap(User.class, CopyUser.class).field("age", "copyAge").byDefault().register();
//        //进行集合复制
//        List<CopyUser> copyUsers = mapperFactory.getMapperFacade().mapAsList(userList, CopyUser.class);
//        //输出
//        System.out.println(copyUsers);
    }


}

@Data
@AllArgsConstructor
class OrikaVo {
    private String name;
    private Integer age;
}

@Data
@AllArgsConstructor
class CopyOrikaVo {
    private String name;
    private Integer age;
}