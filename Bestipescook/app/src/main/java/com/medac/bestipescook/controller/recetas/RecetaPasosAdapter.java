package com.medac.bestipescook.controller.recetas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.IHostingData;
import com.squareup.picasso.Picasso;

public class RecetaPasosAdapter extends RecyclerView.Adapter<RecetaPasosAdapter.ViewHolder>  implements View.OnClickListener{

    private Context context;
    private View.OnClickListener listener;

    public RecetaPasosAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflated = LayoutInflater.from(context);
        View view = inflated.inflate(R.layout.fragment_receta_paso, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String sPasoTexto = RecetaStore.lstPasos.get(position).getsTextoPaso();
        String sOrden = String.valueOf(RecetaStore.lstPasos.get(position).getbOrdenPaso());
        String sRutaUrl = IHostingData.sHosting + IHostingData.sRutaImagenes +RecetaStore.lstPasos.get(position).getImagen().getsRutaUrlImagen();

        holder.lblPasoTexto.setText(sOrden + ". " + sPasoTexto);
        Picasso.get().load(sRutaUrl).into(holder.ivPaso);
    }

    @Override
    public int getItemCount() {return RecetaStore.lstPasos.size();}

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView lblPasoTexto;
        private ImageView ivPaso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblPasoTexto = itemView.findViewById(R.id.lblPasoReceta);
            ivPaso = itemView.findViewById(R.id.ivRecetaPaso);
        }
    }

}
