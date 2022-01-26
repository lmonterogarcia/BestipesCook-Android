package com.medac.bestipescook.controller.retos;

import com.medac.bestipescook.model.Reto;

import java.util.ArrayList;

public class RetoStore {

    public static ArrayList<Reto> lstRetos = new ArrayList<Reto>();
    public static int iRetoSeleccionada;

    /**
     * @param reto
     * Introduce una reto en un lugar ordenando por idReto
     */
    public static void aniadirReto(Reto reto){
        if (lstRetos.size() > 1) {
            int iPosicion = 0;
            boolean retoAniadida = false;
            do {
                if (reto.getiIdReto() < lstRetos.get(iPosicion).getiIdReto()){
                    lstRetos.add(iPosicion, reto);
                    retoAniadida = true;
                }
                iPosicion++;
            }while(!retoAniadida && iPosicion < lstRetos.size());
            if (!retoAniadida){
                lstRetos.add(reto);
            }
        } else if (lstRetos.size() == 1 && lstRetos.get(0).getiIdReto() > reto.getiIdReto()) {
            lstRetos.add(0, reto);
        } else {
            lstRetos.add(reto);
        }
        frRetos.adaptador.notifyDataSetChanged();
    }
    
}
