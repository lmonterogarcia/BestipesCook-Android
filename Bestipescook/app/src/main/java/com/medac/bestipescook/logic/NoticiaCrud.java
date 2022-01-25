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
import com.medac.bestipescook.model.noticia.INoticia;
import com.medac.bestipescook.model.noticia.Noticia;
import com.medac.bestipescook.view.noticias.frNoticias;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class NoticiaCrud implements IHostingData, INoticia {


    public static void getAllNoticias(Context context, final VolleyCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstNoticias;
        Log.d("Pruebas", url);

        // Request a string response from the provided URL.
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
                            rellenarLstNoticias(context, lstObjetos, callBack);
                    }
                }, error -> {
            Toast.makeText(context, "Ha habido nu error al recuperar las noticias. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
            Log.d("Bestipes" , error.toString());
        });

        // Add the request to the RequestQueue.

            queue.add(stringRequest);
            queue.start();

    }

    private static void rellenarLstNoticias(Context context, List<Map<String, Object>> lstObjetos, final VolleyCallBack callBack) {

        for (int i = 0 ; i < lstObjetos.size() ; i++){
            final int j = i;
            ImagenCrud.getImagenLstNoticias(context, Integer.parseInt(lstObjetos.get(i).get("imagenidImagen").toString()), new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    aniadirNoticia(lstObjetos.get(j));
                    if (j == lstObjetos.size() - 1){
                        callBack.onSuccess();
                    }
                }});
        }

    }

    private static void aniadirNoticia(Map<String, Object> noticia) {
        NoticiaStore.lstNoticias.add( new Noticia(
                Integer.parseInt(noticia.get("idNoticia").toString()),
                LocalDateTime.parse(noticia.get("fechaCreacionNoticia").toString(), INoticia.dateTimeformatter),
                noticia.get("tituloNoticia").toString(),
                noticia.get("subtituloNoticia").toString(),
                noticia.get("textoNoticia").toString(),
                new Imagen(
                        ImagenCrud.iIdIamgen,
                        LocalDateTime.parse(ImagenCrud.fechaCreacionIamgen,
                                INoticia.dateTimeformatter),ImagenCrud.sRutaUrl)
        ));

        Collections.sort(NoticiaStore.lstNoticias, Comparator.comparingInt(Noticia::getIdNoticia));
        frNoticias.adaptador.notifyDataSetChanged();
    }
}
