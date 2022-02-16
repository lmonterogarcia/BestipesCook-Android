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
import com.medac.bestipescook.model.usuario.Usuario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuentaCrud {
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
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sInsertUsuario +"?txtMail="+ sMail +"&txtNombreUsuario="+sUsuario+"&txtPasswordUsuario="+sPass;
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
}
