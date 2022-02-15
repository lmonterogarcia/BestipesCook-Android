package com.medac.bestipescook.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.cuenta.frCuenta;
import com.medac.bestipescook.controller.cuenta.frEditarPerfil;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImgPicker extends AppCompatActivity {
    private static final int SELECT_FILE = 1;
    private ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_picker);

        imagen = findViewById(R.id.img_btn);

        imagen.setOnClickListener(e ->{
            cargarImagen();
        });

        findViewById(R.id.btn_Aceptar).setOnClickListener(e ->{
            this.onBackPressed();
            frEditarPerfil.img.setImageBitmap(frEditarPerfil.bmp);
        });
    }

    private void cargarImagen(){
        Intent intent = new Intent (Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(
                Intent.createChooser(intent, "Seleccione una imagen"),
                SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        Uri selectedImage;

        String filePath = null;
        switch (requestCode) {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            frEditarPerfil.bmp = BitmapFactory.decodeStream(imageStream);
                            frEditarPerfil.urlLocal = selectedPath;
                            Log.d("Pruebas4",selectedPath);
                            Log.d("Pruebas4",selectedImage.toString());
                            Log.d("Pruebas4",imageReturnedIntent.getDataString());

                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                            ImageView mImg = findViewById(R.id.img_btn);
                            mImg.setImageBitmap(frEditarPerfil.bmp);

                            frEditarPerfil.bmp = getCroppedBitmap(frEditarPerfil.bmp);
                        }
                    }
                }
                break;
        }
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        Bitmap _bmp = Bitmap.createScaledBitmap(output, 250, 250, false);
        return _bmp;
    }
}