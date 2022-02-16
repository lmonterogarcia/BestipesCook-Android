package com.medac.bestipescook.controller.cuenta;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;


import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.ClienteFTP;
import com.medac.bestipescook.controller.ImgPicker;
import com.medac.bestipescook.controller.ImgPicker2;
import com.medac.bestipescook.logic.CuentaCrud;
import com.medac.bestipescook.logic.ImagenCrud;
import com.medac.bestipescook.model.usuario.Usuario;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import android.widget.ImageView;

import org.w3c.dom.Text;

public class frEditarPerfil extends Fragment {
    private static final int IMAGE_PICKER_SELECT = 0;
    private View v;
    private byte bGenero = 0;
    public static Bitmap bmp = null;
    public static String urlLocal = "";
    public static ImageView img;
    public static EditText fecha;

    public frEditarPerfil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        TextView lblUsuario = v.findViewById(R.id.lblUsuarioEdit);
        TextView lblMail = v.findViewById(R.id.lblEmailEdit);
        fecha = v.findViewById(R.id.txtFechaNacimiento);

        lblUsuario.setText(CuentaCrud.preferencias.getString("usuario", ""));
        lblMail.setText(CuentaCrud.preferencias.getString("mail", ""));

        img = v.findViewById(R.id.btnImg);

        if(CuentaCrud.oObjeto != null){
            Log.d("pruebas3","actualiza editar");
            TextView txtName = v.findViewById(R.id.txtName);
            txtName.setText(CuentaCrud.oObjeto.get("nombreCompletoUsuario"));
            TextView txtCodigoPostal = v.findViewById(R.id.txtCodigoPostal);
            txtCodigoPostal.setText(CuentaCrud.oObjeto.get("codigoPostalUsuario"));
            TextView txtFechaNacimiento = v.findViewById(R.id.txtFechaNacimiento);
            txtFechaNacimiento.setText(CuentaCrud.oObjeto.get("fechaNacimientoUsuario"));

            bGenero = Byte.parseByte(CuentaCrud.oObjeto.get("generoUsuario"));
            switch (bGenero){
                case 0:
                    MaterialButton btn_Otro = v.findViewById(R.id.btnOtro);
                    btn_Otro.setChecked(true);
                    break;
                case 1:
                    MaterialButton btn_Hombre = v.findViewById(R.id.btnHombre);
                    btn_Hombre.setChecked(true);
                    break;
                case 2:
                    MaterialButton btn_Mujer = v.findViewById(R.id.btnMujer);
                    btn_Mujer.setChecked(true);
                    break;
            }

            Spinner spn = v.findViewById(R.id.spnPais);
            spn.setSelection(Integer.parseInt(CuentaCrud.oObjeto.get("paisUsuario")));
        }

        if(bmp != null){
            RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), bmp);
            roundDrawable.setCircular(true);
            img.setImageDrawable(roundDrawable);
        }

        v.findViewById(R.id.txtFechaNacimiento).setOnClickListener(e ->{
            showDatePickerDialog(v);
        });

        v.findViewById(R.id.btnImg).setOnClickListener(e -> {
            Intent intent = new Intent(getActivity(), ImgPicker.class);
            startActivity(intent);
        });

        v.findViewById(R.id.btnHombre).setOnClickListener(e -> {
            bGenero = 1;
        });

        v.findViewById(R.id.btnMujer).setOnClickListener(e -> {
            bGenero = 2;
        });

        v.findViewById(R.id.btnOtro).setOnClickListener(e -> {
            bGenero = 0;
        });

        v.findViewById(R.id.btnGuardar).setOnClickListener(e -> {
            guardarDatos();

        });
        return v;
    }

    private void guardarDatos() {
            Usuario oUsuario = new Usuario();
            oUsuario.setsEmailUsuario(CuentaCrud.preferencias.getString("mail", ""));
            oUsuario.setsNombreUsuraio(CuentaCrud.preferencias.getString("usuario", ""));
            oUsuario.setsPassUsuario(CuentaCrud.preferencias.getString("pass", ""));

            TextView txtName = v.findViewById(R.id.txtName);
            TextView txtCodigoPostal = v.findViewById(R.id.txtCodigoPostal);
            Spinner txtSpinner = v.findViewById(R.id.spnPais);

            oUsuario.setsNombreCompletoUsuario(txtName.getText().toString());

            //convert String to LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = fecha.getText().toString();
            LocalDate localDate = LocalDate.parse(date, formatter);

            oUsuario.setFechaNacimientoUsuario(localDate);
            oUsuario.setsCodigoPostalUsuario(txtCodigoPostal.getText().toString());
            oUsuario.setbGeneroUsuario(bGenero);
            oUsuario.setiPaisUsuario(txtSpinner.getSelectedItemPosition());


            if(bmp != null){
                File f = new File(urlLocal);
                ClienteFTP.start(true);
                ImagenCrud.insertImage(getContext(), f.getName(), () ->{
                    CuentaCrud.updUsuario(getContext(), oUsuario, () -> actualizarDatos());
                } );
            }else{
                CuentaCrud.updUsuario(getContext(), oUsuario, () -> actualizarDatos());
            }

        }

    private void actualizarDatos() {
        CuentaCrud.getUsuario(getContext(),CuentaCrud.preferencias.getString("usuario", ""),CuentaCrud.preferencias.getString("pass", ""),() -> abrirFragmentCuenta());
    }

    private void abrirFragmentCuenta() {
        frCuenta nextFrag = new frCuenta();
        if (!nextFrag.isAdded()) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void showDatePickerDialog(View v) {
        frDatePicker newFragment = new frDatePicker();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
        }

}