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
        Log.d("Pruebas","ENTRA 1");
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstNoticias;

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Log.d("Pruebas","ENTRA 4");
                        Toast.makeText(context, "ENTRA 4", Toast.LENGTH_LONG).show();
                        Toast.makeText(context, "No hay noticias disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("Pruebas","ENTRA 2");
                        Toast.makeText(context, "ENTRA 2", Toast.LENGTH_LONG).show();
                        ArrayList<JSONObject> lstObjetos =  new Gson().fromJson(s,new TypeToken<List<JSONObject>>() {}.getType());
                        rellenarLstNoticias(lstObjetos, NoticiaStore.lstNoticias);
                    }

                }, VolleyError -> {
            Toast.makeText(context, VolleyError.toString(), Toast.LENGTH_LONG).show();
            Log.d("Pruebas", VolleyError.getStackTrace().toString());
        }));
        Log.d("Pruebas","ENTRA 3");
    }

    private static void rellenarLstNoticias(ArrayList<JSONObject> lstObjetos, ArrayList lstNoticias) {
        lstObjetos.forEach(noticia ->{
            try {
                lstNoticias.add(new Noticia(
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

    /*private static void rellenarLstNoticias(ArrayList<JSONObject> lstObjetos) {
        lstObjetos.forEach(noticia ->{
            NoticiaStore.lstNoticias.add(new Noticia(
                    Integer.parseInt(quitarComillas(noticia.get("idNoticia").toString())),
                    LocalDateTime.parse(quitarComillas(noticia.get("fechaCreacionNoticia").toString()), INoticia.dateTimeformatter),
                    quitarComillas(noticia.get("tituloNoticia").toString()),
                    quitarComillas(noticia.get("subtituloNoticia").toString()),
                    quitarComillas(noticia.get("textoNoticia").toString()),
                    new Imagen(1,LocalDateTime.parse(quitarComillas(noticia.get("fechaCreacionNoticia").toString()), INoticia.dateTimeformatter),"fotovacia.png") // ESTO   ES UNA PRUEBA
            ));
        });
    }*/

    /*private static String quitarComillas(String sCampo){

        return sCampo.substring(1,sCampo.length() - 1);
    }*/


}
