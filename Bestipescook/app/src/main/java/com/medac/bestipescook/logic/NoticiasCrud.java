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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NoticiasCrud implements IHostingData, INoticia {

    public static void getAllNoticias(Context context) {

        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstNoticias;

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
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
        Log.d("Pruebas","ENTRA 2");
    }

    private static void rellenarLstNoticias(ArrayList<JSONObject> lstObjetos) {
        lstObjetos.forEach(noticia ->{
            try {
                NoticiaStore.lstNoticias.add(new Noticia(
                        noticia.getInt("idNoticia"),
                        LocalDateTime.parse(noticia.getString("fechaCreacionNoticia"), INoticia.dateTimeformatter),
                        noticia.getString("tituloNoticia"),
                        noticia.getString("subtituloNoticia"),
                        noticia.getString("textoNoticia"),
                        new Imagen(1,LocalDateTime.parse(noticia.getString("fechaCreacionNoticia"), INoticia.dateTimeformatter),"fotovacia.png") // ESTO   ES UNA PRUEBA
                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

}
