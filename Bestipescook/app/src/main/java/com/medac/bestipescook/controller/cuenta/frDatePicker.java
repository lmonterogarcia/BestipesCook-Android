package com.medac.bestipescook.controller.cuenta;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;


import java.util.Calendar;

public class frDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private DatePickerDialog.OnDateSetListener listener;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String sMes = (month + 1) + "";
            String sDia = day + "";
            if((month + 1) < 10){
                sMes = "0" + (month + 1) + "";
            }
            if(day < 10){
                sDia = "0" + day + "";
            }
            frEditarPerfil.fecha.setText(year+"-"+sMes+"-"+sDia);
        }
}