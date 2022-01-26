package com.medac.bestipescook.logic;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.Noticia;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoticiaCrud implements IHostingData, IConstantes {


    public static void getAllNoticias(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstNoticias;
        Log.d("Pruebas", url);

        // Request a string Para conseguir todas las noticias.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay noticias disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllNoticias");
                            e.printStackTrace();
                        }
                            rellenarLstNoticias(context, lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Ha habido nu error al recuperar las noticias. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
            Log.d("Bestipes" , error.toString());
        });

        // Add the request to the RequestQueue.
            queue.add(stringRequest);
            queue.start();

    }

    private static void rellenarLstNoticias(Context context, List<Map<String, Object>> lstObjetos) {

        lstObjetos.forEach(n ->{
            ImagenCrud.getImagenLstNoticias(context, Integer.parseInt(n.get("imagenidImagen").toString()), () -> aniadirNoticia(n)); // La funcion Lamda es un VolleyCallBack.
        });
    }

    private static void aniadirNoticia(Map<String, Object> noticia) {
        NoticiaStore.aniadirNoticia( new Noticia(
                Integer.parseInt(noticia.get("idNoticia").toString()),
                LocalDateTime.parse(noticia.get("fechaCreacionNoticia").toString(), IConstantes.dateTimeformatter),
                noticia.get("tituloNoticia").toString(),
                noticia.get("subtituloNoticia").toString(),
                noticia.get("textoNoticia").toString(),
                new Imagen(
                        ImagenCrud.iIdIamgen,
                        LocalDateTime.parse(ImagenCrud.fechaCreacionIamgen,
                                IConstantes.dateTimeformatter),ImagenCrud.sRutaUrl)
        ));
    }
}
