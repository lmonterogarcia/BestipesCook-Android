package com.medac.bestipescook.logic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medac.bestipescook.controller.cuenta.CuentaRecetaDetalle;
import com.medac.bestipescook.controller.noticias.frNoticias;
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.controller.recetas.Receta_detalle;
import com.medac.bestipescook.controller.recetas.frRecetas;
import com.medac.bestipescook.controller.recetas.frRecetasPasos;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.categoria.Categoria;
import com.medac.bestipescook.model.receta.Ingrediente;
import com.medac.bestipescook.model.receta.IngredienteReceta;
import com.medac.bestipescook.model.receta.Paso;
import com.medac.bestipescook.model.receta.Receta;
import com.medac.bestipescook.model.usuario.Usuario;
import com.medac.bestipescook.model.usuario.UsuarioRecetaEstrella;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecetaCrud implements IHostingData, IConstantes {

    public static void getAllRecetas(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstRecetas;

        // Request a string Para conseguir todas las recetas.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "No hay recetas disponibles en este momento", Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s, new TypeReference<List<Map<String, Object>>>() {
                            });
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRecetas");
                            e.printStackTrace();
                        }
                        rellenarLstRecetas(lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar las recetas. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }

    public static void getAllRecetasSearch(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstRecetasSearch + frRecetas.query;

        // Request a string Para conseguir todas las recetas.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "No hay recetas disponibles en este momento", Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s, new TypeReference<List<Map<String, Object>>>() {
                            });
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRecetas");
                            e.printStackTrace();
                        }
                        rellenarLstRecetas(lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar las recetas. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }

    public static void getIngredientes(Context context, int iIdReceta, final VolleyCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sGetIngredientes + iIdReceta;

        // Request a string Para conseguir todas las ingredientes.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Error leer los ingredientes", Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s, new TypeReference<List<Map<String, Object>>>() {
                            });
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getIngredientes");
                            e.printStackTrace();
                        }
                        rellenarLstIngredientes(lstObjetos, callBack);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar los ingredientes. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }

    public static void getAllPasos(Context context, int iIdReceta) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sGetPasos + iIdReceta;

        // Request a string Para conseguir todas las ingredientes.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Error leer los pasos", Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s, new TypeReference<List<Map<String, Object>>>() {
                            });
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllPasos");
                            e.printStackTrace();
                        }
                        rellenarLstPasos(lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar los pasos. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }

    public static void usuarioGustaReceta(Context context, String sNombreUsuario, int iIdReceta, VolleyCallBack callBack) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = sHosting + sAndroid + sGetMeGusta + iIdReceta + sUsuario + sNombreUsuario;

        // Request a string Para conseguir todas las ingredientes.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Error leer si le gusta", Toast.LENGTH_LONG).show();
                    } else {
                        if (s.equals("true")){
                            RecetaStore.booMeGusta = true;
                        } else {
                            RecetaStore.booMeGusta = false;
                        }
                        callBack.onSuccess();
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar los me gusta. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();

    }

    public static void insMegusta(Context context, String sNombreUsuario, int iIdReceta, VolleyCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = sHosting + sAndroid + sInsMeGusta + iIdReceta + sUsuario + sNombreUsuario;

        // Request a string Para conseguir todas las ingredientes.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Error leer si le gusta", Toast.LENGTH_LONG).show();
                    } else {
                        callBack.onSuccess();
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar los me gusta. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }

    public static void delMegusta(Context context, String sNombreUsuario, int iIdReceta, VolleyCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = sHosting + sAndroid + sDelMeGusta + iIdReceta + sUsuario + sNombreUsuario;

        // Request a string Para conseguir todas las ingredientes.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Error leer si le gusta", Toast.LENGTH_LONG).show();
                    } else {
                        callBack.onSuccess();
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar los me gusta. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }
    public static void puntuarReceta(Context context, float fRating, String sNombreUsuario, int iIdReceta, VolleyCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = sHosting + sAndroid + sValorarReceta + iIdReceta + sUsuario + sNombreUsuario + sPuntuacionReceta + (int)fRating;

        // Request a string Para conseguir todas las ingredientes.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Error al leer las pùntuaciones", Toast.LENGTH_LONG).show();
                    } else {
                        callBack.onSuccess();
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar lasm puntuaciones. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }

    private static void rellenarLstIngredientes(List<Map<String, Object>> lstObjetos, VolleyCallBack callBack) {
        RecetaStore.lstIngredientes.clear();
        lstObjetos.forEach(i -> {
            RecetaStore.lstIngredientes.add(new IngredienteReceta(
                    //Receta oReceta, Ingrediente oIngrediente, int iCantidadIngrediente, int iMedida
                    new Receta(Integer.parseInt(i.get("idReceta").toString())),
                    new Ingrediente(Integer.parseInt(i.get("idIngrediente").toString()), i.get("nombreIngrediente").toString()),
                    Integer.parseInt(i.get("cantidadIngrediente").toString()),
                    Integer.parseInt(i.get("medida").toString())
            ));
        });
        callBack.onSuccess();
    }

    private static void rellenarLstPasos(List<Map<String, Object>> lstObjetos) {
        RecetaStore.lstPasos.clear();
        lstObjetos.forEach(i -> {
            RecetaStore.lstPasos.add(new Paso(
                    //int iIdPaso, String sTextoPaso, byte bOrdenPaso, Receta receta, Imagen imagen
                    Integer.parseInt(i.get("idPaso").toString()),
                    i.get("textoPaso").toString(),
                    Byte.parseByte(i.get("ordenPaso").toString()),
                    new Receta(Integer.parseInt(i.get("idReceta").toString())),
                    new Imagen(i.get("rutaRelativaImagen").toString())
            ));
        });
        if (Receta_detalle.adaptador != null) {
            Receta_detalle.adaptador.notifyDataSetChanged();
        }
        if (CuentaRecetaDetalle.adaptador != null) {
            CuentaRecetaDetalle.adaptador.notifyDataSetChanged();
        }

    }

    private static void rellenarLstRecetas(List<Map<String, Object>> lstObjetos) {
        lstObjetos.forEach(n -> {
            aniadirReceta(n);
        });
    }

    private static void aniadirReceta(Map<String, Object> receta) {

        RecetaStore.aniadirReceta(new Receta(
                        Integer.parseInt(receta.get("idReceta").toString()),
                        LocalDateTime.parse(receta.get("fechaCreacionReceta").toString(), IConstantes.dateTimeformatterFromDB),
                        receta.get("tituloReceta").toString(),
                        receta.get("textoReceta").toString(),
                        Boolean.parseBoolean(receta.get("enRevision").toString()),
                        new Usuario(receta.get("usuarionombreUsuario").toString(), receta.get("rutaImgUsuario").toString()),
                        new Categoria(Integer.parseInt(receta.get("idCategoria").toString()), receta.get("nombreCategoria").toString()),
                        Short.parseShort(receta.get("comensalesReceta").toString()),
                        Float.parseFloat(receta.get("duracionReceta").toString())),

                (new Imagen(
                        Integer.parseInt(String.valueOf(receta.get("idImagen"))),
                        LocalDateTime.parse(String.valueOf(receta.get("fechaCreacionImagen")),
                                IConstantes.dateTimeformatterFromDB), String.valueOf(receta.get("rutaRelativaImagen")))),

                Float.parseFloat(receta.get("puntuacionMedia").toString()));
    }

    public static void getAllCategorias(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstRecetas;

        // Request a string Para conseguir todas las recetas.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "No hay recetas disponibles en este momento", Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s, new TypeReference<List<Map<String, Object>>>() {
                            });
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRecetas");
                            e.printStackTrace();
                        }
                        rellenarLstRecetas(lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar las recetas. Intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
            Log.d("Bestipes", error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }
}
