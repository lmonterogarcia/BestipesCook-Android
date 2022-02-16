package com.medac.bestipescook.model.usuario;

import com.medac.bestipescook.model.Imagen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {

    // PK
    private String sNombreUsuraio;
    // NN
    private LocalDateTime fechaCreacionUsuario;
    private String sEmailUsuario, sPassUsuario;
    private byte bGeneroUsuario;
    private boolean booBaneadoUsuario, booENRevisionUsuario, booAdmin;
    // N
    private String sNombreCompletoUsuario, sCodigoPostalUsuario;
    private LocalDate fechaNacimientoUsuario;
    private int iPaisUsuario;
    Imagen imagen ;

    // ESTO NO  ES ASI.
	private int imagenidImagen; // ESTO HAY SE SETEA EN IMAGEN, ARRIBA

    public Usuario() {
    }

    public Usuario(String sNombreUsuraio) {
        this.sNombreUsuraio = sNombreUsuraio;
    }

    public Usuario(String sNombreUsuraio, String sRutaImagen) {

        this.sNombreUsuraio = sNombreUsuraio;
        this.imagen = new Imagen(0, sRutaImagen);
    }

    public Usuario(String sNombreUsuraio, LocalDateTime fechaCreacionUsuario, String sEmailUsuario, String sPassUsuario,
                   byte bGeneroUsuario, boolean booBaneadoUsuario, boolean booENRevisionUsuario, boolean booAdmin) {
        this.sNombreUsuraio = sNombreUsuraio;
        this.fechaCreacionUsuario = fechaCreacionUsuario;
        this.sEmailUsuario = sEmailUsuario;
        this.sPassUsuario = sPassUsuario;
        this.bGeneroUsuario = bGeneroUsuario;
        this.booBaneadoUsuario = booBaneadoUsuario;
        this.booENRevisionUsuario = booENRevisionUsuario;
        this.booAdmin = booAdmin;
    }

    public Usuario(String sNombreUsuraio, LocalDateTime fechaCreacionUsuario, String sEmailUsuario, String sPassUsuario,
                   byte bGeneroUsuario, boolean booBaneadoUsuario, boolean booENRevisionUsuario, boolean booAdmin,
                   String sNombreCompletoUsuario, String sCodigoPostalUsuario, LocalDate fechaNacimientoUsuario,
                   int iPaisUsuario, int imagenidImagen) {
        this.sNombreUsuraio = sNombreUsuraio;
        this.fechaCreacionUsuario = fechaCreacionUsuario;
        this.sEmailUsuario = sEmailUsuario;
        this.sPassUsuario = sPassUsuario;
        this.bGeneroUsuario = bGeneroUsuario;
        this.booBaneadoUsuario = booBaneadoUsuario;
        this.booENRevisionUsuario = booENRevisionUsuario;
        this.booAdmin = booAdmin;
        this.sNombreCompletoUsuario = sNombreCompletoUsuario;
        this.sCodigoPostalUsuario = sCodigoPostalUsuario;
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
        this.iPaisUsuario = iPaisUsuario;
        this.imagenidImagen = imagenidImagen;
    }

    public String getsNombreUsuraio() {
        return sNombreUsuraio;
    }

    public void setsNombreUsuraio(String sNombreUsuraio) {
        this.sNombreUsuraio = sNombreUsuraio;
    }

    public LocalDateTime getFechaCreacionUsuario() {
        return fechaCreacionUsuario;
    }

    public void setFechaCreacionUsuario(LocalDateTime fechaCreacionUsuario) {
        this.fechaCreacionUsuario = fechaCreacionUsuario;
    }

    public String getsEmailUsuario() {
        return sEmailUsuario;
    }

    public void setsEmailUsuario(String sEmailUsuario) {
        this.sEmailUsuario = sEmailUsuario;
    }

    public String getsPassUsuario() {
        return sPassUsuario;
    }

    public void setsPassUsuario(String sPassUsuario) {
        this.sPassUsuario = sPassUsuario;
    }

    public byte getbGeneroUsuario() {
        return bGeneroUsuario;
    }

    public void setbGeneroUsuario(byte bGeneroUsuario) {
        this.bGeneroUsuario = bGeneroUsuario;
    }

    public boolean isBooBaneadoUsuario() {
        return booBaneadoUsuario;
    }

    public void setBooBaneadoUsuario(boolean booBaneadoUsuario) {
        this.booBaneadoUsuario = booBaneadoUsuario;
    }

    public boolean isBooENRevisionUsuario() {
        return booENRevisionUsuario;
    }

    public void setBooENRevisionUsuario(boolean booENRevisionUsuario) {
        this.booENRevisionUsuario = booENRevisionUsuario;
    }

    public boolean isBooAdmin() {
        return booAdmin;
    }

    public void setBooAdmin(boolean booAdmin) {
        this.booAdmin = booAdmin;
    }

    public String getsNombreCompletoUsuario() {
        return sNombreCompletoUsuario;
    }

    public void setsNombreCompletoUsuario(String sNombreCompletoUsuario) {
        this.sNombreCompletoUsuario = sNombreCompletoUsuario;
    }

    public String getsCodigoPostalUsuario() {
        return sCodigoPostalUsuario;
    }

    public void setsCodigoPostalUsuario(String sCodigoPostalUsuario) {
        this.sCodigoPostalUsuario = sCodigoPostalUsuario;
    }

    public LocalDate getFechaNacimientoUsuario() {
        return fechaNacimientoUsuario;
    }

    public void setFechaNacimientoUsuario(LocalDate fechaNacimientoUsuario) {
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
    }

    public int getiPaisUsuario() {
        return iPaisUsuario;
    }

    public void setiPaisUsuario(int iPaisUsuario) {
        this.iPaisUsuario = iPaisUsuario;
    }

    public int getImagenidImagen() {
        return imagenidImagen;
    }

    public void setImagenidImagen(int imagenidImagen) {
        this.imagenidImagen = imagenidImagen;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sNombreUsuraio);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(sNombreUsuraio, other.sNombreUsuraio);
    }
}
