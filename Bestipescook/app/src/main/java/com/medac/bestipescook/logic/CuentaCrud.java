package com.medac.bestipescook.logic;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
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

    public static void getUsuario(Context context, String nombreUsuario, String passwordUsuario) {
        Log.d("Pruebas", "LLEGA AQUI TAMBIEN");
        String url = IHostingData.sHosting + IHostingData.sAndroid + IHostingData.sGetUsuario +"?txtNombreUsuario="+ nombreUsuario+"&txtPasswordUsuario="+passwordUsuario;
        //Toast.makeText(context, url,Toast.LENGTH_LONG).show();
        Log.d("Pruebas", url);

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, url,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(context, "Usuario o contraseña inválida ", Toast.LENGTH_LONG).show();
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        Map<String, String> oObjeto = new HashMap<String, String>();
                        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                        try {
                            oObjeto = mapper.readValue(s, new TypeReference<Map<String, String>>() {
                            });
                        } catch (IOException e) {
                            Log.d("Pruebas", "El parseo del Map no correcto en getAllNoticias");
                            e.printStackTrace();
                        }
                        guardarUsuario(oObjeto);
                    }
                }
                , VolleyError -> {
        }));
    }

    private static void guardarUsuario(Map<String, String> oObjeto) {
        nombreUsuario = oObjeto.get("nombreUsuario").toString();
        passUsuario = oObjeto.get("passwordUsuario").toString();
        File file = new File("app\\src\\main\\java\\com\\medac\\bestipescook\\model\\usuario\\LoginData.txt");
        Log.d("PruebasWritter", "Crea el File");
        try {
            FileWriter fileWriter = new FileWriter(file);
            Log.d("PruebasWritter", "Crea el FileWriter");
            BufferedWriter bw = new BufferedWriter(fileWriter);
            Log.d("PruebasWritter", "Crea el Buffer Writer");
            bw.write(nombreUsuario+":"+passUsuario);
            Log.d("PruebasWritter", "Esribe los datos en el fichero");
            bw.close();
            Log.d("PruebasWritter", "Cierra el buffer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
