package com.medac.bestipescook.controller.cuenta;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.CuentaCrud;
import com.medac.bestipescook.model.usuario.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class frEditarPerfil extends Fragment {
    private static final int IMAGE_PICKER_SELECT = 0;
    private View v;
    private byte bGenero = 0;
    private TextView profileIv;

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
        v =  inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        profileIv = v.findViewById(R.id.btnImg);

        TextView lblUsuario = v.findViewById(R.id.lblUsuarioEdit);
        TextView lblMail = v.findViewById(R.id.lblEmailEdit);

        lblUsuario.setText(CuentaCrud.preferencias.getString("usuario",""));
        lblMail.setText(CuentaCrud.preferencias.getString("mail",""));

        v.findViewById(R.id.btnImg).setOnClickListener(e -> {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, IMAGE_PICKER_SELECT);
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
            Usuario oUsuario = new Usuario();
            oUsuario.setsEmailUsuario(CuentaCrud.preferencias.getString("mail",""));
            oUsuario.setsNombreUsuraio(CuentaCrud.preferencias.getString("usuario",""));
            oUsuario.setsPassUsuario(CuentaCrud.preferencias.getString("pass",""));

            TextView txtName = v.findViewById(R.id.txtName);
            TextView txtFecha = v.findViewById(R.id.txtFechaNacimiento);
            TextView txtCodigoPostal = v.findViewById(R.id.txtCodigoPostal);
            Spinner txtSpinner = v.findViewById(R.id.spnPais);

            oUsuario.setsNombreCompletoUsuario(txtName.getText().toString());

            //convert String to LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = txtFecha.getText().toString();
            LocalDate localDate = LocalDate.parse(date, formatter);

            oUsuario.setFechaNacimientoUsuario(localDate);
            oUsuario.setsCodigoPostalUsuario(txtCodigoPostal.getText().toString());
            oUsuario.setbGeneroUsuario(bGenero);
            oUsuario.setiPaisUsuario(txtSpinner.getSelectedItemPosition());

            CuentaCrud.updUsuario(getContext(),oUsuario);

            frCuenta nextFrag = new frCuenta();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER_SELECT
                && resultCode == Activity.RESULT_OK) {
            String path = getPathFromCameraData(data, this.getActivity());
            if (path != null) {
                setFullImageFromFilePath(mImgProfile, path);
            }
        }
    }

    public static String getPathFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }
}