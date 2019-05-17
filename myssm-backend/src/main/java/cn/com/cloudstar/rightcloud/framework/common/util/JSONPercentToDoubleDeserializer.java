/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.NumberFormat;

public class JSONPercentToDoubleDeserializer extends JsonDeserializer<Double> {

    private static Log logger = LogFactory.getLog(JSONPercentToDoubleDeserializer.class);

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Double value = null;
        try {
            String text = jsonParser.getText();
            NumberFormat percentFormat = NumberFormat.getPercentInstance();
            value = percentFormat.parse(text).doubleValue();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return value;
    }

}	
