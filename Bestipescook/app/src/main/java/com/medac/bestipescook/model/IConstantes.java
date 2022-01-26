package com.medac.bestipescook.model;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public interface IConstantes {

        public DateTimeFormatter dateTimeformatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .optionalStart()
                .appendPattern(".")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 6, false)
                .optionalEnd()
                .toFormatter(); //LocalDateTime dateTime = LocalDateTime.parse(str, formatter);


}
