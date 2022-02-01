package com.medac.bestipescook.model.categoria;

import java.util.Objects;

public class Categoria {
	private int idCategoria;
	private String nombreCategoria;
	private boolean challengue;
	
	public Categoria(int idCategoria) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = "";
		this.challengue = false;
	}
	
	public Categoria(int idCategoria, String nombreCategoria) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.challengue = false;
	}
	
	public Categoria(int idCategoria, String nombreCategoria, boolean challengue) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.challengue = challengue;
	}


	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public boolean isChallengue() {
		return challengue;
	}

	public void setChallengue(boolean challengue) {
		this.challengue = challengue;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + ", challengue="
				+ challengue + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return idCategoria == other.idCategoria;
	}
	
}
