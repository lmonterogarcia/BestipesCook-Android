package com.medac.bestipescook.model;

import com.medac.bestipescook.R;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public interface IConstantes {

        public static DateTimeFormatter dateTimeformatterFromDB = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .optionalStart()
                .appendPattern(".")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 6, false)
                .optionalEnd()
                .toFormatter(); //LocalDateTime dateTime = LocalDateTime.parse(str, formatter);


        public static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        public static DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        public static DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");

        // RETOS
        public static String sFechaFinalizacionHasta = String.valueOf((R.string.texto_fecha_reto));
        public static String sFechaFinalizacionHastaDetalle = String.valueOf((R.string.texto_fecha_reto_detalle));

        // Ordenar un Arraylist segun PK integer. Esto no va a qui, pero para que se sepa como ordenar un arraylist de objetos personalizados.
        // Collections.sort(NoticiaStore.lstNoticias, Comparator.comparingInt(Noticia::getIdNoticia));
}
