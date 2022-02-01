package com.medac.bestipescook.model.categoria;

import java.util.Objects;
import com.medac.bestipescook.model.receta.Receta;

public class CategoriaReceta {

	private Categoria oCategoria;
	private Receta oReceta;
	
	public CategoriaReceta(Categoria oCategoria, Receta oReceta) {
		this.oCategoria = oCategoria;
		this.oReceta = oReceta;
	}

	public Categoria getoCategoria() {
		return oCategoria;
	}

	public void setoCategoria(Categoria oCategoria) {
		this.oCategoria = oCategoria;
	}

	public Receta getoReceta() {
		return oReceta;
	}

	public void setoReceta(Receta oReceta) {
		this.oReceta = oReceta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(oCategoria, oReceta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaReceta other = (CategoriaReceta) obj;
		return Objects.equals(oCategoria, other.oCategoria) && Objects.equals(oReceta, other.oReceta);
	}
	
	
	
	
}
