package cn.com.cloudstar.rightcloud.system.activiti.util;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * activiti ID生成器
 * @author yuz
 * @date 2018/12/28
 */
public class DistributedIdGenerator implements IdGenerator {
    @Override
    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}
