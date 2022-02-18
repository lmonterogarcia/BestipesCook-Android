package com.medac.bestipescook.controller.recetas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.RecetaCrud;

public class frRecetasPasos extends Fragment {

    private View v;
    public static RecetaPasosAdapter adaptador;

    public frRecetasPasos() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_receta_detalle, container, false);
        cargarPasos();
        return v;
    }

    private void cargarPasos()  {

        RecetaStore.lstPasos.clear();;
        RecetaCrud.getAllPasos(getContext(), RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta());
        mostrarRecetas();
    }

    private void mostrarRecetas() {

        RecyclerView rvRecetas = v.findViewById(R.id.rvRecetas);

        rvRecetas.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new RecetaPasosAdapter(getContext());
        rvRecetas.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {

        });
    }

}
