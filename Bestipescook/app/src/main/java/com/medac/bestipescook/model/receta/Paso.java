package com.medac.bestipescook.model.receta;

import com.medac.bestipescook.model.Imagen;

import java.util.Objects;

public class Paso {
	
	// PK
	private int iIdPaso;
	//NN
	private String sTextoPaso;
	private byte bOrdenPaso;
	private Receta receta;
	//N
	private Imagen imagen;
	
	public Paso() {
	}

	public Paso(int iIdPaso) {
		this.iIdPaso = iIdPaso;
	}

	public Paso(int iIdPaso, String sTextoPaso, byte bOrdenPaso, Receta receta, Imagen imagen) {
		this.iIdPaso = iIdPaso;
		this.sTextoPaso = sTextoPaso;
		this.bOrdenPaso = bOrdenPaso;
		this.receta = receta;
		this.imagen = imagen;
	}

	public int getiIdPaso() {
		return iIdPaso;
	}

	public void setiIdPaso(int iIdPaso) {
		this.iIdPaso = iIdPaso;
	}

	public String getsTextoPaso() {
		return sTextoPaso;
	}

	public void setsTextoPaso(String sTextoPaso) {
		this.sTextoPaso = sTextoPaso;
	}

	public byte getbOrdenPaso() {
		return bOrdenPaso;
	}

	public void setbOrdenPaso(byte bOrdenPaso) {
		this.bOrdenPaso = bOrdenPaso;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iIdPaso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paso other = (Paso) obj;
		return iIdPaso == other.iIdPaso;
	}

}
