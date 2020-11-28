/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author ShiWenQiang
 * @date 2016/11/22
 */
public class FreeMarkerUtil {

    private static Logger logger = LoggerFactory.getLogger(FreeMarkerUtil.class);

    private static FreeMarkerUtil freeMarkerUtil;

    private Configuration configuration;


    public static FreeMarkerUtil getInstance() {
        if (freeMarkerUtil == null) {
            synchronized (FreeMarkerUtil.class) {
                if (freeMarkerUtil == null) {
                    freeMarkerUtil = new FreeMarkerUtil();
                }
            }
        }
        return freeMarkerUtil;
    }

    public FreeMarkerUtil withClassResourceLoaderClassBasePackagePath(Class resourceLoaderClass,
                                                                      String basePackagePath) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(resourceLoaderClass, basePackagePath);
        cfg.setDefaultEncoding("UTF-8");
        this.setConfiguration(cfg);
        return this;
    }

    public FreeMarkerUtil withFileDir(File dir) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(dir);
        this.setConfiguration(cfg);
        return this;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
