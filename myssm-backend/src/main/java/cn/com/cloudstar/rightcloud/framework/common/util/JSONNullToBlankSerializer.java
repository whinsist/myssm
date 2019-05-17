/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class JSONNullToBlankSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object object, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
            throws IOException {
        paramJsonGenerator.writeString("");
    }
}
