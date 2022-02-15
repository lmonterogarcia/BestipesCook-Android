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
import java.util.function.LongFunction;

public class StartRateCrud {

    public static int iIdReceta;
    public static float bPuntucaionReceta;

    public static void getRate(Context context, int iIdReceta, final VolleyCallBack callBack){
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sGetStarRate + iIdReceta;

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET,url,
                s -> {
                    if(s.equals("")) {
                        Toast.makeText(context, "No se ha encontrado la puntuacion con la id de la receta: " + iIdReceta,Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String, String> oObjeto = new HashMap<String, String>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            oObjeto = mapper.readValue(s , new TypeReference<Map<String, String>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getStarRate");
                            e.printStackTrace();
                        }
                        rellenarRate(oObjeto);
                    }
                }
                , VolleyError -> {
        }));
    }

    private static void rellenarRate(Map<String, String> oObjeto) {
        bPuntucaionReceta = Float.parseFloat(oObjeto.get("puntuacionMedia").toString());

    }
}
