package com.alura.forohub.DTO;

import com.alura.forohub.modelo.Curso;
import com.alura.forohub.modelo.Status;
import com.alura.forohub.modelo.Topico;
import com.alura.forohub.modelo.Usuario;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String autor,
        String curso) {

    public DatosListadoTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
