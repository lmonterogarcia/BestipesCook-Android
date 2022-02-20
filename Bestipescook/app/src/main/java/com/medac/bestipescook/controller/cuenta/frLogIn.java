package com.medac.bestipescook.controller.cuenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.CuentaCrud;

public class frLogIn extends Fragment {
    private View v;
    public frLogIn() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_log_in, container, false);
        v.findViewById(R.id.btnEntrar).setOnClickListener(e ->{
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            logearse();
        });

        v.findViewById(R.id.btnRegistro).setOnClickListener(e ->{
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            frCrearCuenta nextFrag = new frCrearCuenta();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

    private void logearse() {
        TextInputEditText user = v.findViewById(R.id.txtMail);
        TextInputEditText pass = v.findViewById(R.id.txtPass);
        CuentaCrud.getUsuario(getContext(),user.getEditableText().toString(),pass.getEditableText().toString(),() -> abrirFragmentCuenta());
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
}