package com.medac.bestipescook.controller.cuenta;

import com.medac.bestipescook.controller.recetas.frRecetas;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.receta.Receta;
import com.medac.bestipescook.model.usuario.UsuarioRecetaEstrella;

import java.util.ArrayList;

public class CuentaRecetaStore {
    public static ArrayList<Receta> lstRecetas = new ArrayList<Receta>();
    public static ArrayList<UsuarioRecetaEstrella> lstPuntuacion = new ArrayList<UsuarioRecetaEstrella>();
    public static ArrayList<Imagen> lstImagenes = new ArrayList<Imagen>();
    public static int iRecetaSeleccionada;

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
        frCuenta.adaptador.notifyDataSetChanged();
    }
}
