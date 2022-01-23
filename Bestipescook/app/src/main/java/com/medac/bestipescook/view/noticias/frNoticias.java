package com.medac.bestipescook.view.noticias;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaAdapter;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.logic.IHostingData;
import com.medac.bestipescook.logic.NoticiasCrud;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.noticia.Noticia;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class frNoticias extends Fragment {

    private View v;
    public frNoticias() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_noticias, container, false);

        cargarNoticias();

        return v;
    }

    private void cargarNoticias()  {
        NoticiasCrud n = new NoticiasCrud();
        NoticiaStore.lstNoticias.clear();
        getAllNoticias();
        NoticiaStore.lstNoticias.size();
        mostarNoticias();


        /*DateTimeFormatter dateTimeformatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .optionalStart()
                .appendPattern(".")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 6, false)
                .optionalEnd()
                .toFormatter(); LocalDateTime dateTime = LocalDateTime.parse("2021-12-12 09:25:20", dateTimeformatter);

        NoticiaStore.lstNoticias.add(new Noticia(1,dateTime, "a","a","a", new Imagen(1,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "z","z","z", new Imagen(2,dateTime,"fotovacia.png")));
*/
    }

    private void mostarNoticias() {

        RecyclerView rvNoticias = v.findViewById(R.id.rvNoticias);

        rvNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        NoticiaAdapter adaptador = new NoticiaAdapter(getContext());
        rvNoticias.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            NoticiaStore.iNoticiaSeleccionada = rvNoticias.getChildAdapterPosition(v);
            Toast.makeText(getContext(), "" + NoticiaStore.iNoticiaSeleccionada, Toast.LENGTH_LONG).show();
            //Intent i = new Intent(this, Noticia_detalle.class);
            //startActivity(i);

        });
    }

    public void getAllNoticias() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstNoticias;
        Log.d("Pruebas", url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("null")) {
                            Toast.makeText(getContext(), "No hay noticias disponibles en este momento",Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("Pruebas","ENTRA 1");
                            Toast.makeText(getContext(), "ENTRA 2", Toast.LENGTH_LONG).show();
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
                            NoticiasCrud.rellenarLstNoticias(lstObjetos);
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


        /*Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
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
        Log.d("Pruebas","ENTRA 2");*/

        /*Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
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
        Log.d("Pruebas","ENTRA 2");*/
    }

    /*private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction =getActivity().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }*/
}