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
import com.medac.bestipescook.controller.ranking.RankingStore;
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.controller.recetas.frRecetas;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.categoria.Categoria;
import com.medac.bestipescook.model.receta.Receta;
import com.medac.bestipescook.model.usuario.Usuario;
import com.medac.bestipescook.model.usuario.UsuarioRecetaEstrella;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingCrud implements IHostingData, IConstantes {


    public static void getAllRecetas(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstRecetas;

        // Request a string Para conseguir todas las recetas.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay recetas disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRecetas");
                            e.printStackTrace();
                        }
                        rellenarLstRecetas(context, lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar las recetas. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
            Log.d("Bestipes" , error.toString());
        });

        // Add the request to the RequestQueue.
            queue.add(stringRequest);
            queue.start();

    }

    public static void getAllRecetasSearch(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstRecetasSearch + frRecetas.query;

        // Request a string Para conseguir todas las recetas.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay recetas disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRecetas");
                            e.printStackTrace();
                        }
                        rellenarLstRecetas(context, lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar las recetas. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
            Log.d("Bestipes" , error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();

    }

    public static void getAllCategorias(Context context,  final VolleyCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstCategorias;

        // Request a string Para conseguir todas las recetas.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay recetas disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRecetas");
                            e.printStackTrace();
                        }
                        rellenarLstCategorias(context, lstObjetos);
                        callBack.onSuccess();
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar las recetas. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
            Log.d("Bestipes" , error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();

    }

    private static void rellenarLstRecetas(Context context, List<Map<String, Object>> lstObjetos) {
        lstObjetos.forEach(n ->{
            aniadirReceta(n);
        });
    }

    private static void rellenarLstCategorias(Context context, List<Map<String, Object>> lstObjetos) {
        RankingStore.lstCategorias = new ArrayList<String>();
        lstObjetos.forEach(n ->{
            aniadirCategoria(n);
        });
    }

    private static void aniadirReceta(Map<String, Object> receta) {

        RecetaStore.aniadirRecetaRank( new Receta(
                        Integer.parseInt(receta.get("idReceta").toString()),
                        LocalDateTime.parse(receta.get("fechaCreacionReceta").toString(), IConstantes.dateTimeformatterFromDB),
                        receta.get("tituloReceta").toString(),
                        receta.get("textoReceta").toString(),
                        Boolean.parseBoolean(receta.get("enRevision").toString()),
                        new Usuario(receta.get("usuarionombreUsuario").toString()),
                        Short.parseShort(receta.get("comensalesReceta").toString()),
                        Float.parseFloat(receta.get("duracionReceta").toString())),
                        

                (new Imagen(
                        Integer.parseInt(String.valueOf(receta.get("idImagen"))),
                        LocalDateTime.parse(String.valueOf(receta.get("fechaCreacionImagen")),
                                IConstantes.dateTimeformatterFromDB),String.valueOf(receta.get("rutaRelativaImagen")))),

                (new UsuarioRecetaEstrella(
                        Integer.parseInt(receta.get("idReceta").toString()), Float.parseFloat(receta.get("puntuacionMedia").toString())))
        );
    }

    private static void aniadirCategoria(Map<String, Object> categoria) {
        //Rellenar el arrayList de categorias para el ranking
        for(Map.Entry<String, Object> entry : categoria.entrySet()) {
            if(entry.getKey().equals("nombreCategoria")){
                RankingStore.lstCategorias.add(entry.getValue().toString());
            }
        }
    }
}
