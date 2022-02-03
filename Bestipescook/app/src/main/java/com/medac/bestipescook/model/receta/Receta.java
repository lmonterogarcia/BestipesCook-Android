package com.medac.bestipescook.model.receta;

import com.medac.bestipescook.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Objects;


public class Receta {

	// PK
	private int iIdReceta;
	// NN
	private LocalDateTime fechaCreacionReceta;
	private String sTituloReceta;
	private String sTextoReceta;
	private boolean booEnREvision;
	private Usuario usuario;
	// N
	private short shComensalesReceta;
	private float fDuracionReceta;
	
	public Receta() {
	}

	public Receta(int iIdReceta) {
		this.iIdReceta = iIdReceta;
	}

	public Receta(int iIdReceta, LocalDateTime fechaCreacionReceta, String sTituloReceta, boolean booEnREvision,
			Usuario usuario) {
		this.iIdReceta = iIdReceta;
		this.fechaCreacionReceta = fechaCreacionReceta;
		this.sTituloReceta = sTituloReceta;
		this.booEnREvision = booEnREvision;
		this.usuario = usuario;
	}

	public Receta(int iIdReceta, LocalDateTime fechaCreacionReceta, String sTituloReceta, String sTextoReceta, boolean booEnREvision, Usuario usuario, short shComensalesReceta, float fDuracionReceta) {
		this.iIdReceta = iIdReceta;
		this.fechaCreacionReceta = fechaCreacionReceta;
		this.sTituloReceta = sTituloReceta;
		this.sTextoReceta = sTextoReceta;
		this.booEnREvision = booEnREvision;
		this.usuario = usuario;
		this.shComensalesReceta = shComensalesReceta;
		this.fDuracionReceta = fDuracionReceta;
	}

	public int getiIdReceta() {
		return iIdReceta;
	}

	public void setiIdReceta(int iIdReceta) {
		this.iIdReceta = iIdReceta;
	}

	public LocalDateTime getFechaCreacionReceta() {
		return fechaCreacionReceta;
	}

	public void setFechaCreacionReceta(LocalDateTime fechaCreacionReceta) {
		this.fechaCreacionReceta = fechaCreacionReceta;
	}

	public String getsTituloReceta() {
		return sTituloReceta;
	}

	public void setsTituloReceta(String sTituloReceta) {
		this.sTituloReceta = sTituloReceta;
	}

	public String getsTextoReceta() {
		return sTextoReceta;
	}

	public void setsTextoReceta(String sTextoReceta) {
		this.sTextoReceta = sTextoReceta;
	}

	public boolean isBooEnREvision() {
		return booEnREvision;
	}

	public void setBooEnREvision(boolean booEnREvision) {
		this.booEnREvision = booEnREvision;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public short getShComensalesReceta() {
		return shComensalesReceta;
	}

	public void setShComensalesReceta(short shComensalesReceta) {
		this.shComensalesReceta = shComensalesReceta;
	}

	public float getfDuracionReceta() {
		return fDuracionReceta;
	}

	public void setfDuracionReceta(float fDuracionReceta) {
		this.fDuracionReceta = fDuracionReceta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Receta receta = (Receta) o;
		return iIdReceta == receta.iIdReceta && booEnREvision == receta.booEnREvision && shComensalesReceta == receta.shComensalesReceta && Float.compare(receta.fDuracionReceta, fDuracionReceta) == 0 && Objects.equals(fechaCreacionReceta, receta.fechaCreacionReceta) && Objects.equals(sTituloReceta, receta.sTituloReceta) && Objects.equals(sTextoReceta, receta.sTextoReceta) && Objects.equals(usuario, receta.usuario);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iIdReceta, fechaCreacionReceta, sTituloReceta, sTextoReceta, booEnREvision, usuario, shComensalesReceta, fDuracionReceta);
	}

	@Override
	public String toString() {
		return "Receta{" +
				"iIdReceta=" + iIdReceta +
				", fechaCreacionReceta=" + fechaCreacionReceta +
				", sTituloReceta='" + sTituloReceta + '\'' +
				", sTextoReceta='" + sTextoReceta + '\'' +
				", booEnREvision=" + booEnREvision +
				", usuario=" + usuario +
				", shComensalesReceta=" + shComensalesReceta +
				", fDuracionReceta=" + fDuracionReceta +
				'}';
	}
}
