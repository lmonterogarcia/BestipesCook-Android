package com.medac.bestipescook.model.receta;

import java.util.Objects;

public class Ingrediente {

	// PK
	private int iIdIngrediente;
	//NN
	private String sNombreIngrediente;
	
	public Ingrediente() {

	}

	public Ingrediente(int iIdIngrediente) {
		this.iIdIngrediente = iIdIngrediente;
	}

	public Ingrediente(int iIdIngrediente, String sNombreIngrediente) {
		this.iIdIngrediente = iIdIngrediente;
		this.sNombreIngrediente = sNombreIngrediente;
	}

	public int getiIdIngrediente() {
		return iIdIngrediente;
	}

	public void setiIdIngrediente(int iIdIngrediente) {
		this.iIdIngrediente = iIdIngrediente;
	}

	public String getsNombreIngrediente() {
		return sNombreIngrediente;
	}

	public void setsNombreIngrediente(String sNombreIngrediente) {
		this.sNombreIngrediente = sNombreIngrediente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iIdIngrediente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return iIdIngrediente == other.iIdIngrediente;
	}
	
	
	
	
}
