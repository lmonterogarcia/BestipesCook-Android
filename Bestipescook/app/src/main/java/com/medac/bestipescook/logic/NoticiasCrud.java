package com.medac.bestipescook.logic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NoticiasCrud implements IHostingData {

    public static void getAllNoticias(Context context) {
        String url = IHostingData.sHosting + "noticia/lst-noticias.php";

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url, s -> {
            Toast.makeText(context, s,Toast.LENGTH_LONG).show();
        }, VolleyError ->{
            Toast.makeText(context, VolleyError.toString(),Toast.LENGTH_LONG).show();
            Log.d("Pruebas",VolleyError.toString() );
        }));

        /*Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET,url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay noticias disponibles",Toast.LENGTH_LONG).show();
                    } else {
                        ArrayList<Object> prueba = new ArrayList<>();
                        prueba = new Gson().fromJson(s,new TypeToken<List<Object>>() {}.getType());
                        Toast.makeText(context, prueba.get(0).toString(),Toast.LENGTH_LONG).show();

                    }
                }
                , VolleyError -> {
                    Toast.makeText(context, VolleyError.toString(),Toast.LENGTH_LONG).show();
                    Log.d("Pruebas",VolleyError.toString() );
        }));*/
    }
    public class NoticiaDB {

        private int idNoticia;
        private String fechaCreacionNoticia;
        private String tituloNoticia, subtituloNoticia, textoNoticia;
        private int imagenidImagen;

        public NoticiaDB(int idNoticia, String fechaCreacionNoticia, String tituloNoticia, String subtituloNoticia, String textoNoticia, int imagenIdImagen) {
            this.idNoticia = idNoticia;
            this.fechaCreacionNoticia = fechaCreacionNoticia;
            this.tituloNoticia = tituloNoticia;
            this.subtituloNoticia = subtituloNoticia;
            this.textoNoticia = textoNoticia;
            this.imagenidImagen = imagenIdImagen;
        }

        public int getIdNoticia() {
            return idNoticia;
        }

        public void setIdNoticia(int idNoticia) {
            this.idNoticia = idNoticia;
        }

        public String getFechaCreacionNoticia() {
            return fechaCreacionNoticia;
        }

        public void setFechaCreacionNoticia(String fechaCreacionNoticia) {
            this.fechaCreacionNoticia = fechaCreacionNoticia;
        }

        public String getTituloNoticia() {
            return tituloNoticia;
        }

        public void setTituloNoticia(String tituloNoticia) {
            this.tituloNoticia = tituloNoticia;
        }

        public String getSubtituloNoticia() {
            return subtituloNoticia;
        }

        public void setSubtituloNoticia(String subtituloNoticia) {
            this.subtituloNoticia = subtituloNoticia;
        }

        public String getTextoNoticia() {
            return textoNoticia;
        }

        public void setTextoNoticia(String textoNoticia) {
            this.textoNoticia = textoNoticia;
        }

        public int getImagenidImagen() {
            return imagenidImagen;
        }

        public void setImagenidImagen(int imagenidImagen) {
            this.imagenidImagen = imagenidImagen;
        }
    }

}
