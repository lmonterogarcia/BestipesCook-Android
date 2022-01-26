package com.medac.bestipescook.logic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImagenCrud {

    public static int iIdIamgen;
    public static String fechaCreacionIamgen;
    public static String sRutaUrl;

    public static void getImagenLstNoticias(Context context, int imangenidImagen, final VolleyCallBack callBack){
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sGetImagen + imangenidImagen;
        //Toast.makeText(context, url,Toast.LENGTH_LONG).show();
        Log.d("Pruebas", url);

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET,url,
                s -> {
                    if(s.equals("")) {
                        Toast.makeText(context, "No se ha encontrado la imagen con la id " + imangenidImagen,Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String, String> oObjeto = new HashMap<String, String>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            oObjeto = mapper.readValue(s , new TypeReference<Map<String, String>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllNoticias");
                            e.printStackTrace();
                        }
                        rellenarImagen(oObjeto);
                        callBack.onSuccess();
                    }
                }
                , VolleyError -> {
        }));
    }

    private static void rellenarImagen(Map<String, String> oObjeto) {
        iIdIamgen = Integer.parseInt(oObjeto.get("idImagen").toString());
        fechaCreacionIamgen = oObjeto.get("fechaCreacionImagen").toString();
        sRutaUrl = oObjeto.get("rutaRelativaImagen").toString();
    }
}
