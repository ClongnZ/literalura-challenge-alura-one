package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a")
    List<Autor> listarAutores();

    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :anioBuscado AND a.fallecimiento >= :anioBuscado")
    List<Autor> listarAutoresVivosAnio(@Param("anioBuscado") Integer anioBuscado);
}
