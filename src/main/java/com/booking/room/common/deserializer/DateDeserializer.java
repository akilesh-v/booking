package com.booking.room.common.deserializer;

import com.booking.room.common.utils.DateUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.booking.room.common.utils.DateUtils.getSupportedLocale;


public class DateDeserializer extends JsonDeserializer<Date> {
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", getSupportedLocale());

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return DateUtils.parseDate(p.getValueAsString(), dateFormat);
    }
}