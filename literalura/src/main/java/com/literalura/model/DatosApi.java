package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosApi {

    @JsonAlias("results")
    List<LibroDTO> resultado;


    public List<LibroDTO> getResultado() {
        return resultado;
    }

    public void setResultado(List<LibroDTO> results) {
        this.resultado = results;
    }
}
