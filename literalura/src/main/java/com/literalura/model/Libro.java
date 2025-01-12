package com.literalura.model;

public class Libro {

    private String titulo;
    private String idioma;
    private Integer numeroDescargas;
    private Autor autor;

    public Libro(LibroDTO libroDTO){
        this.titulo = libroDTO.titulo();
        this.idioma = libroDTO.idiomas().get(0);
        this.numeroDescargas = libroDTO.numeroDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }



}
