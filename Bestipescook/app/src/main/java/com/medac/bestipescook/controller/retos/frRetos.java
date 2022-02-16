package com.medac.bestipescook.controller.retos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.RetoCrud;

public class frRetos extends Fragment {

    private View v;
    public static RetoAdapter adaptador;

    public frRetos() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_retos, container, false);
        cargarRetos();

        return v;
    }

    private void cargarRetos()  {

        RetoStore.lstRetos.clear();
        RetoCrud.getAllRetos(getContext());
        mostrarRetos();
    }

    private void mostrarRetos() {

        RecyclerView rvRetos = v.findViewById(R.id.rvRetos);

        rvRetos.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new RetoAdapter(getContext());
        rvRetos.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            RetoStore.iRetoSeleccionada = rvRetos.getChildAdapterPosition(v);
            Reto_detalle nextFrag= new Reto_detalle();
            if (!nextFrag.isAdded()){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "retos")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}