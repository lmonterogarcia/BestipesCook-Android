package com.medac.bestipescook.model.receta;

import java.util.Objects;
import com.medac.bestipescook.model.receta.IIngredienteReceta;

public class IngredienteReceta implements IIngredienteReceta{

	private Receta oReceta;
	private Ingrediente oIngrediente;
	private int iCantidadIngrediente, iMedida;
	
	public IngredienteReceta() {
	}
	
	public IngredienteReceta(Receta oReceta, Ingrediente oIngrediente, int iCantidadIngrediente, int iMedida) {
		this.oReceta = oReceta;
		this.oIngrediente = oIngrediente;
		this.iCantidadIngrediente = iCantidadIngrediente;
		this.iMedida = iMedida;
	}

	public Receta getoReceta() {
		return oReceta;
	}

	public void setoReceta(Receta oReceta) {
		this.oReceta = oReceta;
	}

	public Ingrediente getoIngrediente() {
		return oIngrediente;
	}

	public void setoIngrediente(Ingrediente oIngrediente) {
		this.oIngrediente = oIngrediente;
	}

	public int getiCantidadIngrediente() {
		return iCantidadIngrediente;
	}

	public void setiCantidadIngrediente(int iCantidadIngrediente) {
		if (iCantidadIngrediente >= 0 && iCantidadIngrediente < ICANTIDADINGREDIENTEMAX) {
			this.iCantidadIngrediente = iCantidadIngrediente;
		}
	}

	public int getiMedida() {
		return iMedida;
	}

	public void setiMedida(int iMedida) {
		if (iMedida >= 0 && iMedida < AMEDIDAS.length) {
			this.iMedida = iMedida;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(oIngrediente, oReceta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredienteReceta other = (IngredienteReceta) obj;
		return Objects.equals(oIngrediente, other.oIngrediente) && Objects.equals(oReceta, other.oReceta);
	}
	
	
	
}
