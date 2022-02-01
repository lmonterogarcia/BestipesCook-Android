package com.medac.bestipescook.model.receta;

import java.time.LocalDateTime;
import java.util.Objects;

import model.usuario.Usuario;

public class Receta {

	// PK
	private int iIdReceta;
	// NN
	private LocalDateTime fechaCreacionReceta;
	private String sTituloReceta;
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

	public Receta(int iIdReceta, LocalDateTime fechaCreacionReceta, String sTituloReceta, boolean booEnREvision,
			Usuario usuario, short shComensalesReceta, float fDuracionReceta) {
		this.iIdReceta = iIdReceta;
		this.fechaCreacionReceta = fechaCreacionReceta;
		this.sTituloReceta = sTituloReceta;
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
	public int hashCode() {
		return Objects.hash(iIdReceta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receta other = (Receta) obj;
		return iIdReceta == other.iIdReceta;
	}
	
}
