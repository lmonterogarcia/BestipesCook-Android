package com.medac.bestipescook.model.usuario;

import java.util.Objects;
import com.medac.bestipescook.model.receta.Receta;

public class UsuarioRecetaEstrella {

	private Usuario oUsuario;
	private Receta oReceta;
	private byte bPuntucaionReceta;
	
	public UsuarioRecetaEstrella() {
	}

	public UsuarioRecetaEstrella(Usuario oUsuario, Receta oReceta, byte bPuntucaionReceta) {
		this.oUsuario = oUsuario;
		this.oReceta = oReceta;
		this.bPuntucaionReceta = bPuntucaionReceta;
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

	public void setoReceta(Receta oReceta) {
		this.oReceta = oReceta;
	}

	public byte getbPuntucaionReceta() {
		return bPuntucaionReceta;
	}

	public void setbPuntucaionReceta(byte bPuntucaionReceta) {
		this.bPuntucaionReceta = bPuntucaionReceta;
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
		UsuarioRecetaEstrella other = (UsuarioRecetaEstrella) obj;
		return Objects.equals(oReceta, other.oReceta) && Objects.equals(oUsuario, other.oUsuario);
	}

	

	
}
