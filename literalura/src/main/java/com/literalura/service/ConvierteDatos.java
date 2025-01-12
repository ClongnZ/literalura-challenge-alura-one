package com.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.model.DatosApi;
import com.literalura.model.LibroDTO;

public class ConvierteDatos implements IConvierteDatos{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            DatosApi apiRespuesta = objectMapper.readValue(json, DatosApi.class);

            if (apiRespuesta != null && apiRespuesta.getResultado() != null && !apiRespuesta.getResultado().isEmpty()) {
                LibroDTO libroDTO = apiRespuesta.getResultado().get(0);
                System.out.println("Primer libro de la lista: " + libroDTO);
                return (T) libroDTO;
            } else {
                throw new RuntimeException("No se encontró ningún libro");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error en JSON: " + e.getMessage(), e);
        }
    }
}
