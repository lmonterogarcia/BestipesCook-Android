package com.medac.bestipescook.model.receta;

import java.util.Objects;

import com.medac.bestipescook.model.Imagen;

public class ImagenReceta {

	private Imagen oImagen;
	private Receta oReceta;
	private boolean booFotoPrincipal;
	
	public ImagenReceta() {
	}
	
	public ImagenReceta(Imagen oImagen, Receta oReceta, boolean booFotoPrincipal) {
		this.oImagen = oImagen;
		this.oReceta = oReceta;
		this.booFotoPrincipal = booFotoPrincipal;
	}

	public Imagen getoImagen() {
		return oImagen;
	}

	public void setoImagen(Imagen oImagen) {
		this.oImagen = oImagen;
	}

	public Receta getoReceta() {
		return oReceta;
	}

	public void setoReceta(Receta oReceta) {
		this.oReceta = oReceta;
	}

	public boolean isBooFotoPrincipal() {
		return booFotoPrincipal;
	}

	public void setBooFotoPrincipal(boolean booFotoPrincipal) {
		this.booFotoPrincipal = booFotoPrincipal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(oImagen, oReceta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagenReceta other = (ImagenReceta) obj;
		return Objects.equals(oImagen, other.oImagen) && Objects.equals(oReceta, other.oReceta);
	}
	
}
