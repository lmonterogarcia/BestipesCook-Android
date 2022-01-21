package com.medac.bestipescook.logic;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class NoticiasCrud implements IHostingData {

    public static void getAllCoche(Context context) {
        String url = IHostingData.sHosting + "noticia/lst-noticias.php";
        Toast.makeText(context, url,Toast.LENGTH_LONG).show();

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET,url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay noticias disponibles",Toast.LENGTH_LONG).show();
                    } else {
                        ArrayList<NoticiaDB> prueba = new ArrayList<NoticiaDB>();
                        prueba = new Gson().fromJson(s,new TypeToken<List<NoticiaDB>>() {}.getType());
                        Toast.makeText(context, prueba.get(0).getImagenidImagen(),Toast.LENGTH_LONG).show();
                    }
                }
                , VolleyError -> {
                    Toast.makeText(context, "Hubo un problema al recuperar las noticias.",Toast.LENGTH_LONG).show();
        }));
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
