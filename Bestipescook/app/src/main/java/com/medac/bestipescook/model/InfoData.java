package com.medac.bestipescook.model;

public interface InfoData {
    //####################### DATOS HOSTING #############################
    public final String URI = "https://thecrewdevelopers.com/bestipes/app/desktop/";
    public final String URI_MEDIA = "https://thecrewdevelopers.com/bestipes/app/media/imagenes/";
    public final String PATH_IMG = "//app//media//imagenes";

    // ## RECETA ##
    public final String URI_RECETA = "receta/";
    public final String URI_LSTRECETAS = "lst-recetas.php";
    public final String URI_GETPUNTUACIONESTRELLA = "get-puntuacionestrella.php?txtIdReceta=";
    public final String URI_GETIMAGENBYIDRECETA = "get-imagenbyidreceta.php?txtIdReceta=";
    public final String URI_GETRECETAPASOS = "get-receta-paso.php?txtIdReceta=";
    public final String URI_GETRECETAINGREDIENTES = "get-receta-ingrediente.php?txtIdReceta=";
    public final String URI_DEL_RECETA = "del-receta.php?txtIdReceta=";
    public final String URI_UPD_RECETA = "upd-receta.php?txtIdReceta=";
    public final String URI_ENREVISION = "&txtEnRevision=";


    // ## LOGIN ##
    public final String URI_LOGIN = "login/login.php";
}
