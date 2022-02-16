package com.medac.bestipescook.logic;

public interface IHostingData {

    public final String sHosting = "https://thecrewdevelopers.com/bestipes/app/";
    public final String sAndroid = "android/";
    public final String sRutaImagenes = "media/imagenes/";

    // NOTICIA
    public final String sLstNoticias = "noticia/lst-noticias.php";

    // IMAGEN
    public final String sGetImagen = "imagen/get-imagen.php?txtidImagen=";
    public final String sInsertImage = "imagen/ins-imagen.php";

    // RETO
    public final String sLstRetos  = "reto/lst-retos.php";

    // RECETA
    public final String sLstRecetas = "receta/lst-recetas.php";

    public final String sLstRecetasSearch = "receta/lst-recetasSearch.php?txtNombreIngrediente=";

    public final String sGetRecetaImagen = "receta/get-imagenIdReceta.php?txtIdReceta=";

    public final String sGetStarRate = "receta/get-puntuacionestrella.php?txtIdReceta=";


    // USUARIO
    public final String sGetUsuario = "usuario/get-usuario.php";
    public final String sInsertUsuario = "usuario/insert-usuario.php";
    public final String sUpdateUsuario = "usuario/upd-usuario.php";

}
