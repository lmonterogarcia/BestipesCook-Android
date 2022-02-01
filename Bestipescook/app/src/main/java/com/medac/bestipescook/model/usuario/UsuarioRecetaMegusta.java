package com.medac.bestipescook.model.usuario;

import com.medac.bestipescook.model.receta.Receta;

import java.util.Objects;

public class UsuarioRecetaMegusta {

	private Usuario oUsuario;
	private Receta oReceta;
	
	public UsuarioRecetaMegusta() {
	}

	public UsuarioRecetaMegusta(Usuario oUsuario, Receta oREceta) {
		this.oUsuario = oUsuario;
		this.oReceta = oREceta;
	}

	public Usuario getoUsuario() {
		return oUsuario;
	}

	public void setoUsuario(Usuario oUsuario) {
		this.oUsuario = oUsuario;
	}

	public Receta getoReceta() {
		return oReceta;
	}

	public void setoReceta(Receta oREceta) {
		this.oReceta = oREceta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(oReceta, oUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRecetaMegusta other = (UsuarioRecetaMegusta) obj;
		return Objects.equals(oReceta, other.oReceta) && Objects.equals(oUsuario, other.oUsuario);
	}
	
}
