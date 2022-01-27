package com.medac.bestipescook.controller.retos;

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

public class RetoAdapter extends RecyclerView.Adapter<RetoAdapter.ViewHolder> implements View.OnClickListener{

    private Context context;
    private View.OnClickListener listener;

    public RetoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RetoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflated = LayoutInflater.from(context);
        View view = inflated.inflate(R.layout.reto_item, parent, false);
        view.setOnClickListener(this);
        return new RetoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetoAdapter.ViewHolder holder, int position) {

        String sTituloReto = RetoStore.lstRetos.get(position).getsTituloReto();
        String sSubtituloReto = RetoStore.lstRetos.get(position).getsSubtituloReto();
        String sRutaUrl = IHostingData.sHosting + IHostingData.sRutaImagenes + RetoStore.lstRetos.get(position).getImagen().getsRutaUrlImagen();

        holder.lblRetoTitulo.setText(sTituloReto);
        holder.lblRetoSubtitulo.setText(sSubtituloReto);
        Picasso.get().load(sRutaUrl).into(holder.ivReto);

    }

    @Override
    public int getItemCount() {
        return RetoStore.lstRetos.size();
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

        private TextView lblRetoTitulo;
        private TextView lblRetoSubtitulo;
        private ImageView ivReto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblRetoTitulo = itemView.findViewById(R.id.lblRetoTitulo);
            lblRetoSubtitulo = itemView.findViewById(R.id.lblRetoSubtitulo);
            ivReto = itemView.findViewById(R.id.ivReto);
        }
    }
}
