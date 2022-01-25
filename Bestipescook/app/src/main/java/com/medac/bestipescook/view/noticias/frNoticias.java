package com.medac.bestipescook.view.noticias;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaAdapter;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.logic.IHostingData;
import com.medac.bestipescook.logic.NoticiaCrud;
import com.medac.bestipescook.logic.VolleyCallBack;
import com.medac.bestipescook.model.noticia.Noticia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


public class frNoticias extends Fragment {

    private View v;
    public static NoticiaAdapter adaptador;

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

        NoticiaStore.lstNoticias.clear();
        NoticiaCrud.getAllNoticias(getContext(),new VolleyCallBack() {
            @Override
            public void onSuccess() {
                //mostrarNoticias();
            }});
        mostrarNoticias();
    }

    private void mostrarNoticias() {

        RecyclerView rvNoticias = v.findViewById(R.id.rvNoticias);

        rvNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new NoticiaAdapter(getContext());
        rvNoticias.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            NoticiaStore.iNoticiaSeleccionada = rvNoticias.getChildAdapterPosition(v);
            Noticia_detalle nextFrag= new Noticia_detalle();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();

        });
    }

    /*public void getAllNoticias() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstNoticias;
        Log.d("Pruebas", url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(getContext(), "No hay noticias disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllNoticias");
                            e.printStackTrace();
                        }
                        NoticiaCrud.rellenarLstNoticias(lstObjetos);
                        mostrarNoticias();
                    }
                }, error -> {
                    Toast.makeText(getContext(), "Ha habido nu error al recuperar las noticias. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
                    Log.d("Bestipes" , error.getMessage());
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }*/
}