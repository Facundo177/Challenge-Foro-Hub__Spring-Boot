package com.alura.forohub.DTO;

import com.alura.forohub.modelo.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Long autorId,
        Long cursoId

    ) {
}
