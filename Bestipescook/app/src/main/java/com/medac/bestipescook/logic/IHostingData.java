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
    public final String sLstRecetasMeGusta = "receta/lst-recetas-megusta-byUser.php";
    public final String sLstRecetasSearch = "receta/lst-recetasSearch.php?txtNombreIngrediente=";
    public final String sGetRecetaImagen = "receta/get-imagenIdReceta.php?txtIdReceta=";
    public final String sGetStarRate = "receta/get-puntuacionestrella.php?txtIdReceta=";
    public final String sGetIngredientes = "receta/get-receta-ingrediente.php?txtIdReceta=";
    public final String sGetPasos = "receta/get-receta-paso.php?txtIdReceta=";
    public final String sGetMeGusta = "receta/get-megusta.php?txtIdReceta=";
    public final String sInsMeGusta = "receta/ins-megusta.php?txtIdReceta=";
    public final String sDelMeGusta = "receta/del-megusta.php?txtIdReceta=";
    public final String sUsuario = "&txtNombreUsuario=";

    // RANKING
    public final String sLstCategorias = "ranking/lst-categorias.php";
    public final String sLstRanking = "ranking/lst-rankingFiltrado.php?txtNombreCategoria=";
    public final String sLstRanking2 = "&?OpcionFiltrar=";

    // USUARIO
    public final String sGetUsuario = "usuario/get-usuario.php";
    public final String sInsertUsuario = "usuario/insert-usuario.php";
    public final String sUpdateUsuario = "usuario/upd-usuario.php";
}
