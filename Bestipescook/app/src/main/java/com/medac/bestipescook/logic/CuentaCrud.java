package com.medac.bestipescook.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medac.bestipescook.controller.cuenta.CuentaRecetaStore;
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.receta.Receta;
import com.medac.bestipescook.model.usuario.Usuario;
import com.medac.bestipescook.model.usuario.UsuarioRecetaEstrella;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuentaCrud implements IHostingData, IConstantes{
    public static String nombreUsuario;
    public static String passUsuario;
    public static String mailUsuario;
    public static SharedPreferences preferencias = null;
    public static Map<String, String> oObjeto = null;

    public static void getUsuario(Context context, String nombreUsuario, String passwordUsuario, final VolleyCallBack callBack) {
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sGetUsuario +"?txtNombreUsuario="+ nombreUsuario+"&txtPasswordUsuario="+passwordUsuario;
        //Toast.makeText(context, url,Toast.LENGTH_LONG).show();
        Log.d("Pruebas", url);

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Usuario o contraseña inválida ", Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        oObjeto = new HashMap<String, String>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            oObjeto = mapper.readValue(s, new TypeReference<Map<String, String>>() {
                            });
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllNoticias");
                            e.printStackTrace();
                        }
                        guardarUsuario(context,oObjeto);
                        callBack.onSuccess();
                    }
                }
                , VolleyError -> {
        }));
    }

    private static void guardarUsuario(Context context, Map<String, String> oObjeto) {
        nombreUsuario = oObjeto.get("nombreUsuario").toString();
        passUsuario = oObjeto.get("passwordUsuario").toString();
        mailUsuario = oObjeto.get("emailUsuario").toString();

        preferencias = context.getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("usuario", nombreUsuario);
        editor.putString("pass", passUsuario);
        editor.putString("mail", mailUsuario);
        editor.commit();

        mostrarDatos(context);
    }

    private static void mostrarDatos(Context context){
        Log.d("pruebas2", preferencias.getString("usuario",""));
        Log.d("pruebas2", preferencias.getString("pass",""));
        Log.d("pruebas2", preferencias.getString("mail",""));
    }


    public static void insertUsuario(Context context, String sMail, String sUsuario, String sPass, final VolleyCallBack callBack) {
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sInsertUsuario +"?txtNombreUsuario="+sUsuario+"&txtMail="+sMail+"&txtPasswordUsuario="+sPass;
        //Toast.makeText(context, url,Toast.LENGTH_LONG).show();
        Log.d("Pruebas", url);

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals(null)) {
                        Toast.makeText(context, "Error al crear un usuario", Toast.LENGTH_LONG).show();
                    } else {
                        Map<String, String> oObjeto = new HashMap<String, String>();
                        oObjeto.put("nombreUsuario",sUsuario);
                        oObjeto.put("passwordUsuario",sPass);
                        oObjeto.put("emailUsuario",sMail);
                        guardarUsuario(context,oObjeto);
                        callBack.onSuccess();
                    }
                }
                , VolleyError -> {
        }));
    }

    public static void updUsuario(Context context,Usuario oUsuario, final VolleyCallBack callBack) {
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sUpdateUsuario
                +"?txtNombreCompleto="+ oUsuario.getsNombreCompletoUsuario()
                +"&txtGenero="+oUsuario.getbGeneroUsuario()
                +"&txtFechaNacimiento='"+oUsuario.getFechaNacimientoUsuario()
                +"'&txtPais="+oUsuario.getiPaisUsuario()
                +"&txtCodigoPostal="+oUsuario.getsCodigoPostalUsuario()
                +"&txtNombreUsuario="+CuentaCrud.preferencias.getString("usuario","");
        Log.d("Pruebas", url);

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals(null)) {
                        Toast.makeText(context, "Error al actualizar un usuario", Toast.LENGTH_LONG).show();
                    }
                    callBack.onSuccess();
                }
                , VolleyError -> {
        }));
    }

        private static void rellenarLstRecetas(Context context, List<Map<String, Object>> lstObjetos) {
            lstObjetos.forEach(n ->{
                aniadirReceta(n);
            });
        }

        private static void aniadirReceta(Map<String, Object> receta) {

            if(receta.get("usuarionombreUsuario").toString().equals(preferencias.getString("usuario",""))){
                CuentaRecetaStore.aniadirReceta( new Receta(
                                Integer.parseInt(receta.get("idReceta").toString()),
                                LocalDateTime.parse(receta.get("fechaCreacionReceta").toString(), IConstantes.dateTimeformatterFromDB),
                                receta.get("tituloReceta").toString(),
                                receta.get("textoReceta").toString(),
                                Boolean.parseBoolean(receta.get("enRevision").toString()),
                                new Usuario(receta.get("usuarionombreUsuario").toString()),
                                Short.parseShort(receta.get("comensalesReceta").toString()),
                                Float.parseFloat(receta.get("duracionReceta").toString())),

                        (new Imagen(
                                Integer.parseInt(String.valueOf(receta.get("idImagen"))),
                                LocalDateTime.parse(String.valueOf(receta.get("fechaCreacionImagen")),
                                        IConstantes.dateTimeformatterFromDB),String.valueOf(receta.get("rutaRelativaImagen")))),

                        (new UsuarioRecetaEstrella(
                                Integer.parseInt(receta.get("idReceta").toString()), Float.parseFloat(receta.get("puntuacionMedia").toString())))
                );
            }
        }

    public static void getAllRecetas(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sLstRecetas;
        Log.d("prueba8",url);
        // Request a string Para conseguir todas las recetas.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    if(s.equals("null")) {
                        Toast.makeText(context, "No hay recetas disponibles en este momento",Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        List<Map<String, Object>> lstObjetos = new ArrayList<Map<String, Object>>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            lstObjetos = mapper.readValue(s , new TypeReference<List<Map<String, Object>>>(){});
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllRecetas");
                            e.printStackTrace();
                        }
                        rellenarLstRecetas(context, lstObjetos);
                    }
                }, error -> {
            Toast.makeText(context, "Hay error al recuperar las recetas. Intentelo de nuevo mas tarde",Toast.LENGTH_LONG).show();
            Log.d("Bestipes" , error.toString());
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
    }
}
