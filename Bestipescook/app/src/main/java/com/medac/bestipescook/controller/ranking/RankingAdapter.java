package com.medac.bestipescook.controller.ranking;

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

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> implements View.OnClickListener{

    private Context context;
    private View.OnClickListener listener;

    public RankingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflated = LayoutInflater.from(context);
        View view = inflated.inflate(R.layout.fragment_ranking_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String sTituloReceta = RecetaStore.lstRecetas.get(position).getsTituloReceta();
        Float fRateStar = RecetaStore.lstPuntuacion.get(position).getbPuntucaionReceta();

        //TRAER LAS IMAGENES RELACIONADAS CON LA TABLA RECETA IMAGENES

        String sRutaUrl = IHostingData.sHosting + IHostingData.sRutaImagenes + RecetaStore.lstImagenes.get(position).getsRutaUrlImagen();

        holder.lblRecetaTituloRank.setText(sTituloReceta);
        holder.rtBarRank.setRating(fRateStar);
        Log.d("Pruebas", String.valueOf(fRateStar));
        Picasso.get().load(sRutaUrl).into(holder.ivRecetaRank);

    }

    @Override
    public int getItemCount() {
        return RecetaStore.lstRecetas.size();
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

        private TextView lblRecetaTituloRank;
        private ImageView ivRecetaRank;
        private RatingBar rtBarRank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblRecetaTituloRank = itemView.findViewById(R.id.lblRecetaTituloRank);
            ivRecetaRank = itemView.findViewById(R.id.ivRecetaRank);
            rtBarRank = itemView.findViewById(R.id.rtBarRank);
        }
    }
}
