package com.medac.bestipescook.logic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.noticia.INoticia;
import com.medac.bestipescook.model.noticia.Noticia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NoticiasCrud implements IHostingData, INoticia {

    public boolean recibido;

    /*public synchronized void getAllNoticias(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstNoticias;
        Log.d("Pruebas", url);
        recibido = false;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("null")) {
                            Toast.makeText(context, "No hay noticias disponibles en este momento",Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("Pruebas","ENTRA 1");
                            Toast.makeText(context, "ENTRA 2", Toast.LENGTH_LONG).show();
                            //Map<String, String> lstObjetos =  new Gson().fromJson(s,new TypeToken<Map<String,String>>() {}.getType());
                            ObjectMapper mapper = new ObjectMapper();
                            List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                            try {
                                lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                            } catch (IOException e) {
                                Log.d("Pruebas", "Map no correcto. parse.");
                                e.printStackTrace();
                            }
                            Log.d("Pruebas", "Hola que hace");
                            rellenarLstNoticias(lstObjetos);
                            recibido = true;
                            notifyAll();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();


        *//*Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay noticias disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("Pruebas","ENTRA 1");
                        Toast.makeText(context, "ENTRA 2", Toast.LENGTH_LONG).show();
                        ArrayList<JSONObject> lstObjetos =  new Gson().fromJson(s,new TypeToken<List<JSONObject>>() {}.getType());
                        rellenarLstNoticias(lstObjetos);
                    }

                }, VolleyError -> {
            Toast.makeText(context, VolleyError.toString(), Toast.LENGTH_LONG).show();
            Log.d("Pruebas", VolleyError.getStackTrace().toString());
        }));
        Log.d("Pruebas","ENTRA 2");*//*

        *//*Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay noticias disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("Pruebas","ENTRA 1");
                        Toast.makeText(context, "ENTRA 2", Toast.LENGTH_LONG).show();
                        //Map<String, String> lstObjetos =  new Gson().fromJson(s,new TypeToken<Map<String,String>>() {}.getType());
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "Map no correcto. parse.");
                            e.printStackTrace();
                        }
                        Log.d("Pruebas", "Hola que hace");
                        rellenarLstNoticias(lstObjetos);
                    }

                }, VolleyError -> {
            Toast.makeText(context, VolleyError.toString(), Toast.LENGTH_LONG).show();
            Log.d("Pruebas", VolleyError.getStackTrace().toString());
        }));
        Log.d("Pruebas","ENTRA 2");*//*
    }*/

    public static void rellenarLstNoticias(List<Map<String, Object>> lstObjetos) {
        lstObjetos.forEach(noticia ->{
            NoticiaStore.lstNoticias.add(new Noticia(
                    Integer.parseInt(noticia.get("idNoticia").toString()),
                    LocalDateTime.parse(noticia.get("fechaCreacionNoticia").toString(), INoticia.dateTimeformatter),
                    noticia.get("tituloNoticia").toString(),
                    noticia.get("subtituloNoticia").toString(),
                    noticia.get("textoNoticia").toString(),
                    new Imagen(1,LocalDateTime.parse(noticia.get("fechaCreacionNoticia").toString(), INoticia.dateTimeformatter),"fotovacia.png") // ESTO   ES UNA PRUEBA
            ));
            Log.d("Pruebas",noticia.get("idNoticia").toString());

        });
    }

    /*private static void rellenarLstNoticias(ArrayList<JSONObject> lstObjetos) {
        Log.d("Pruebas","" + lstObjetos.size());
        lstObjetos.forEach(noticia ->{
            try {
                NoticiaStore.lstNoticias.add(new Noticia(
                        Integer.parseInt(noticia.getString("idNoticia")),
                        LocalDateTime.parse(noticia.getString("fechaCreacionNoticia"), INoticia.dateTimeformatter),
                        noticia.getString("tituloNoticia"),
                        noticia.getString("subtituloNoticia"),
                        noticia.getString("textoNoticia"),
                        new Imagen(1,LocalDateTime.parse(noticia.getString("fechaCreacionNoticia"), INoticia.dateTimeformatter),"fotovacia.png") // ESTO   ES UNA PRUEBA
                ));
                Log.d("Pruebas",noticia.getString("idNoticia"));
            } catch (JSONException e) {
                Log.d("Pruebas","Entra en JSONEXCEPTION");
                e.printStackTrace();
            }
        });
    }*/

}
