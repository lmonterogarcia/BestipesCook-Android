package com.medac.bestipescook.controller.recetas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.RecetaCrud;


public class frRecetas extends Fragment {

    private View v;
    public static RecetaAdapter adaptador;

    public frRecetas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_recetas, container, false);
        cargarRecetas();

        return v;
    }

    private void cargarRecetas()  {

        RecetaStore.lstRecetas.clear();
        RecetaStore.lstImagenes.clear();
        RecetaCrud.getAllRecetas(getContext());
        mostrarRecetas();
    }

    private void mostrarRecetas() {

        RecyclerView rvRecetas = v.findViewById(R.id.rvRecetas);

        rvRecetas.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new RecetaAdapter(getContext());
        rvRecetas.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            RecetaStore.iRecetaSeleccionada = rvRecetas.getChildAdapterPosition(v);
            Receta_detalle nextFrag= new Receta_detalle();
            if (!nextFrag.isAdded()){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}