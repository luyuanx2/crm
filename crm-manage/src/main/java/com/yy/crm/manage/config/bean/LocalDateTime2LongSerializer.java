package com.yy.crm.manage.config.bean;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author luyuanyuan on 2018/2/27.
 */
public class LocalDateTime2LongSerializer extends LocalDateTimeSerializer {

    private static final long serialVersionUID = 6646978957692554484L;

    @Override
    public void serialize(LocalDateTime value, JsonGenerator g, SerializerProvider provider) throws IOException {
        g.writeNumber(Timestamp.valueOf(value).getTime());
    }

}
