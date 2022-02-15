package com.medac.bestipescook.controller.recetas;

import com.medac.bestipescook.controller.ranking.frRanking;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.categoria.Categoria;
import com.medac.bestipescook.model.receta.Receta;
import com.medac.bestipescook.model.usuario.UsuarioRecetaEstrella;

import java.util.ArrayList;

public class RecetaStore {

    public static ArrayList<Receta> lstRecetas = new ArrayList<Receta>();
    public static ArrayList<Imagen> lstImagenes = new ArrayList<Imagen>();
    public static ArrayList<Categoria> lstCategorias = new ArrayList<Categoria>();
    public static ArrayList<String> lstNombreCategoria = new ArrayList<String>();
    public static ArrayList<UsuarioRecetaEstrella> lstPuntuacion = new ArrayList<UsuarioRecetaEstrella>();
    public static int iRecetaSeleccionada;

    /**
     * @param receta
     * Introduce una receta en un lugar ordenando por idReceta
     */
    public static void aniadirReceta(Receta receta, Imagen imagen, UsuarioRecetaEstrella starRate){
        if (lstRecetas.size() > 1) {
            int iPosicion = 0;
            boolean recetaAniadida = false;
            do {
                if (receta.getiIdReceta() < lstRecetas.get(iPosicion).getiIdReceta()){
                    lstPuntuacion.add(iPosicion, starRate);
                    lstImagenes.add(iPosicion, imagen);
                    lstRecetas.add(iPosicion, receta);
                    recetaAniadida = true;
                }
                iPosicion++;
            }while(!recetaAniadida && iPosicion < lstRecetas.size());
            if (!recetaAniadida){
                lstPuntuacion.add(starRate);
                lstImagenes.add(imagen);
                lstRecetas.add(receta);
            }
        } else if (lstRecetas.size() == 1 && lstRecetas.get(0).getiIdReceta() > receta.getiIdReceta()) {
            lstPuntuacion.add(0, starRate);
            lstImagenes.add(0, imagen);
            lstRecetas.add(0, receta);

        } else {
            lstPuntuacion.add(starRate);
            lstImagenes.add(imagen);
            lstRecetas.add(receta);
        }
        frRecetas.adaptador.notifyDataSetChanged();
    }

    public static void aniadirRecetaRank(Receta receta, Imagen imagen, UsuarioRecetaEstrella starRate){
        if (lstRecetas.size() > 1) {
            int iPosicion = 0;
            boolean recetaAniadida = false;
            do {
                if (receta.getiIdReceta() < lstRecetas.get(iPosicion).getiIdReceta()){
                    lstImagenes.add(iPosicion, imagen);
                    lstPuntuacion.add(iPosicion, starRate);
                    lstRecetas.add(iPosicion, receta);
                    recetaAniadida = true;
                }
                iPosicion++;
            }while(!recetaAniadida && iPosicion < lstRecetas.size());
            if (!recetaAniadida){
                lstImagenes.add(imagen);
                lstPuntuacion.add(starRate);
                lstRecetas.add(receta);
            }
        } else if (lstRecetas.size() == 1 && lstRecetas.get(0).getiIdReceta() > receta.getiIdReceta()) {
            lstImagenes.add(0, imagen);
            lstPuntuacion.add(0, starRate);
            lstRecetas.add(0, receta);

        } else {
            lstImagenes.add(imagen);
            lstPuntuacion.add(starRate);
            lstRecetas.add(receta);
        }
        frRanking.adaptador.notifyDataSetChanged();
    }

    public static void aniadirCategoria(Categoria categoria){
        if (lstCategorias.size() > 1) {
            int iPosicion = 0;
            boolean categoriaAniadida = false;
            do {
                if (categoria.getIdCategoria() < lstCategorias.get(iPosicion).getIdCategoria()){
                    lstCategorias.add(iPosicion, categoria);
                    lstNombreCategoria.add(iPosicion, categoria.getNombreCategoria());
                    categoriaAniadida = true;
                }
                iPosicion++;
            }while(!categoriaAniadida && iPosicion < lstCategorias.size());
            if (!categoriaAniadida){
                lstCategorias.add(categoria);
                lstNombreCategoria.add(categoria.getNombreCategoria());
            }
        } else if (lstCategorias.size() == 1 && lstCategorias.get(0).getIdCategoria() > categoria.getIdCategoria()) {
            lstCategorias.add(0, categoria);
            lstNombreCategoria.add(0, categoria.getNombreCategoria());

        } else {
            lstCategorias.add(categoria);
            lstNombreCategoria.add(categoria.getNombreCategoria());
        }
        frRanking.adaptador.notifyDataSetChanged();
    }

}
