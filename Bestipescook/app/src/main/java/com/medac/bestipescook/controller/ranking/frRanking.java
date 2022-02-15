package com.medac.bestipescook.controller.ranking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.controller.recetas.Receta_detalle;
import com.medac.bestipescook.logic.RankingCrud;


public class frRanking extends Fragment {

    private View v;
    public static RankingAdapter adaptador;
    private Spinner spinerCategoriaLugar;
    public static String query;

    public frRanking() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ranking, container, false);
        spinerCategoriaLugar = v.findViewById(R.id.spCategoria);

        cargarRecetas();
        cargarCategorias();

        return v;
    }

    private void cargarCategorias()  {
        spinerCategoriaLugar.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, RecetaStore.lstNombreCategoria));

    }

    private void cargarRecetas()  {

        RecetaStore.lstRecetas.clear();
        RecetaStore.lstImagenes.clear();
        RecetaStore.lstPuntuacion.clear();
        RankingCrud.getAllRecetas(getContext());
        RankingCrud.getAllCategorias(getContext());
        mostrarRecetas();
    }

    private void mostrarRecetas() {

        RecyclerView rvRanking = v.findViewById(R.id.rvRanking);

        rvRanking.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new RankingAdapter(getContext());
        rvRanking.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            RecetaStore.iRecetaSeleccionada = rvRanking.getChildAdapterPosition(v);
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