package com.medac.bestipescook.controller.cuenta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.logic.IHostingData;
import com.squareup.picasso.Picasso;

public class CuentaAdapter extends RecyclerView.Adapter<CuentaAdapter.ViewHolder> implements View.OnClickListener{

    private Context context;
    private View.OnClickListener listener;

    public CuentaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflated = LayoutInflater.from(context);
        View view = inflated.inflate(R.layout.fragment_receta_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String sTituloReceta = CuentaRecetaStore.lstRecetas.get(position).getsTituloReceta();
        Float fRateStar = CuentaRecetaStore.lstPuntuacion.get(position).getbPuntucaionReceta();

        //TRAER LAS IMAGENES RELACIONADAS CON LA TABLA RECETA IMAGENES

        String sRutaUrl = IHostingData.sHosting + IHostingData.sRutaImagenes + CuentaRecetaStore.lstImagenes.get(position).getsRutaUrlImagen();

        holder.lblRecetaTitulo.setText(sTituloReceta);
        holder.rtBar.setRating(fRateStar);
        Log.d("Pruebas", String.valueOf(fRateStar));
        Picasso.get().load(sRutaUrl).into(holder.ivReceta);

    }

    @Override
    public int getItemCount() {
        return CuentaRecetaStore.lstRecetas.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView lblRecetaTitulo;
        private ImageView ivReceta;
        private RatingBar rtBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblRecetaTitulo = itemView.findViewById(R.id.lblRecetaTitulo);
            ivReceta = itemView.findViewById(R.id.ivReceta);
            rtBar = itemView.findViewById(R.id.rtBar);
        }
    }
}
