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
import com.medac.bestipescook.controller.retos.RetoStore;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.Reto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RetoCrud {

    public static void getAllRetos(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstRetos;
        Log.d("Pruebas", url);

        // Request a string Para conseguir todas las retos.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay retos disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRetos");
                            e.printStackTrace();
                        }
                        rellenarLstRetos(context, lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Ha habido nu error al recuperar las retos. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
            Log.d("Bestipes" , error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();

    }

    private static void rellenarLstRetos(Context context, List<Map<String, Object>> lstObjetos) {

        lstObjetos.forEach(n ->{
            ImagenCrud.getImagen(context, Integer.parseInt(n.get("imagenidImagen").toString()), () -> aniadirReto(n)); // La funcion Lamda es un VolleyCallBack.
        });
    }

    private static void aniadirReto(Map<String, Object> reto) {
        RetoStore.aniadirReto( new Reto(
                Integer.parseInt(reto.get("idReto").toString()),
                LocalDateTime.parse(reto.get("fechaCreacionReto").toString(), IConstantes.dateTimeformatterFromDB),
                LocalDateTime.parse(reto.get("fechaFinalizacionReto").toString(), IConstantes.dateTimeformatterFromDB),
                reto.get("tituloReto").toString(),
                reto.get("subtituloReto").toString(),
                reto.get("textoReto").toString(),
                new Imagen(
                        ImagenCrud.iIdIamgen,
                        LocalDateTime.parse(ImagenCrud.fechaCreacionIamgen,
                                IConstantes.dateTimeformatterFromDB),ImagenCrud.sRutaUrl)
        ));
    }
    
}
