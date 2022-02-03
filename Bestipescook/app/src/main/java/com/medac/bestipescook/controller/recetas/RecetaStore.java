package com.medac.bestipescook.controller.recetas;

import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.receta.Receta;
import com.medac.bestipescook.model.usuario.UsuarioRecetaEstrella;

import java.util.ArrayList;

public class RecetaStore {

    public static ArrayList<Receta> lstRecetas = new ArrayList<Receta>();
    public static ArrayList<Imagen> lstImagenes = new ArrayList<Imagen>();
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
                    lstRecetas.add(iPosicion, receta);
                    lstImagenes.add(iPosicion, imagen);
                    lstPuntuacion.add(iPosicion, starRate);
                    recetaAniadida = true;
                }
                iPosicion++;
            }while(!recetaAniadida && iPosicion < lstRecetas.size());
            if (!recetaAniadida){
                lstRecetas.add(receta);
                lstImagenes.add(imagen);
                lstPuntuacion.add(starRate);
            }
        } else if (lstRecetas.size() == 1 && lstRecetas.get(0).getiIdReceta() > receta.getiIdReceta()) {
            lstRecetas.add(0, receta);
            lstImagenes.add(0, imagen);
            lstPuntuacion.add(0, starRate);
        } else {
            lstRecetas.add(receta);
            lstImagenes.add(imagen);
            lstPuntuacion.add(starRate);
        }
        frRecetas.adaptador.notifyDataSetChanged();
    }

}
