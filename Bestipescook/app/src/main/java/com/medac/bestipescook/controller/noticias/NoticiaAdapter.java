package com.medac.bestipescook.controller.noticias;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medac.bestipescook.R;

import java.io.IOException;
import java.net.URL;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> implements View.OnClickListener{

    private Context context;
    private View.OnClickListener listener;

    public NoticiaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflated = LayoutInflater.from(context);
        View view = inflated.inflate(R.layout.noticia_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String sTituloNoticia = NoticiaStore.lstNoticias.get(position).getTituloNoticia();
        String sSubtituloNoticia = NoticiaStore.lstNoticias.get(position).getSubtituloNoticia();
        String sRutaUrl = NoticiaStore.lstNoticias.get(position).getImagen().getsRutaUrlImagen();

        holder.lblNoticiaTitulo.setText(sTituloNoticia);
        holder.lblNoticiaSubtitulo.setText(sSubtituloNoticia);
        try {
            Bitmap bmImagenNoticia = BitmapFactory.decodeStream(new URL(sRutaUrl).openStream());
            holder.ivNoticia.setImageBitmap(bmImagenNoticia);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return NoticiaStore.lstNoticias.size();
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

        private TextView lblNoticiaTitulo;
        private TextView lblNoticiaSubtitulo;
        private ImageView ivNoticia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblNoticiaTitulo = itemView.findViewById(R.id.lblNoticiaTitulo);
            lblNoticiaSubtitulo = itemView.findViewById(R.id.lblNoticiaSubtitulo);
            ivNoticia = itemView.findViewById(R.id.ivNoticia);
        }
    }
}
